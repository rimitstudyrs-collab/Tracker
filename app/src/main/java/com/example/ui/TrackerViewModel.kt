package com.example.ui

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.AppDatabase
import com.example.data.TrackerRepository
import com.example.data.UserLicenseEntity
import com.example.model.Chapter
import com.example.model.ModuleSection
import com.example.model.PrepTrack
import com.example.model.Subject
import com.example.model.SyllabusData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.Locale

@OptIn(ExperimentalCoroutinesApi::class)
class TrackerViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: TrackerRepository

    init {
        val db = AppDatabase.getDatabase(application)
        repository = TrackerRepository(db.trackerDao(), application.applicationContext)
    }

    val userLicense: StateFlow<UserLicenseEntity?> = repository.userLicense
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), null)

    val allLicenses: StateFlow<List<UserLicenseEntity>> = repository.allLicenses
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    private val _selectedTrack = MutableStateFlow(PrepTrack.VARSITY_A)
    val selectedTrack: StateFlow<PrepTrack> = _selectedTrack.asStateFlow()

    private val _currentView = MutableStateFlow("home")
    val currentView: StateFlow<String> = _currentView.asStateFlow()

    private val _isDarkTheme = MutableStateFlow(true)
    val isDarkTheme: StateFlow<Boolean> = _isDarkTheme.asStateFlow()

    private val _expandedSubjects = MutableStateFlow<Set<String>>(emptySet())
    val expandedSubjects: StateFlow<Set<String>> = _expandedSubjects.asStateFlow()

    private val _expandedChapters = MutableStateFlow<Set<String>>(emptySet())
    val expandedChapters: StateFlow<Set<String>> = _expandedChapters.asStateFlow()

    private val _searchQuery = MutableStateFlow("")
    val searchQuery: StateFlow<String> = _searchQuery.asStateFlow()

    // 10-Minute Demo Trial state
    private val _isDemoActive = MutableStateFlow(false)
    val isDemoActive: StateFlow<Boolean> = _isDemoActive.asStateFlow()

    private val _demoRemainingSeconds = MutableStateFlow(600L) // 10 minutes
    val demoRemainingSeconds: StateFlow<Long> = _demoRemainingSeconds.asStateFlow()

    private var demoTimerJob: Job? = null

    // Study Session Alert & Notification state
    private val _isStudyAlertEnabled = MutableStateFlow(true)
    val isStudyAlertEnabled: StateFlow<Boolean> = _isStudyAlertEnabled.asStateFlow()

    // Auth & Admin UI states
    private val _showAuthScreen = MutableStateFlow(false)
    val showAuthScreen: StateFlow<Boolean> = _showAuthScreen.asStateFlow()

    private val _authError = MutableStateFlow<String?>(null)
    val authError: StateFlow<String?> = _authError.asStateFlow()

    private val _showAdminModal = MutableStateFlow(false)
    val showAdminModal: StateFlow<Boolean> = _showAdminModal.asStateFlow()

    private val _isAdminUnlocked = MutableStateFlow(false)
    val isAdminUnlocked: StateFlow<Boolean> = _isAdminUnlocked.asStateFlow()

    private val _generatedAdminCode = MutableStateFlow<String?>(null)
    val generatedAdminCode: StateFlow<String?> = _generatedAdminCode.asStateFlow()

    private val _adminMessage = MutableStateFlow<String?>(null)
    val adminMessage: StateFlow<String?> = _adminMessage.asStateFlow()

    // Syllabus customization version tracker
    private val _syllabusVersion = MutableStateFlow(0L)
    val syllabusVersion: StateFlow<Long> = _syllabusVersion.asStateFlow()

    // Cached Modules StateFlow for Zero-Lag UI Rendering
    val currentModules: StateFlow<List<ModuleSection>> = combine(
        selectedTrack,
        syllabusVersion
    ) { track, _ ->
        withContext(Dispatchers.IO) {
            val baseModules = SyllabusData.getModulesForTrack(track)
            baseModules.map { module ->
                val subjects = repository.getSubjects(track.id, module.id, module.subjects)
                module.copy(subjects = subjects)
            }
        }
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    // Progress state keyed by: "${trackId}|${subjectName}|${chapterIndex}|${taskId}"
    val progressMap: StateFlow<Map<String, Boolean>> = selectedTrack.flatMapLatest { track ->
        repository.getProgressForTrack(track.id).map { list ->
            list.associate { "${it.trackId}|${it.subjectName}|${it.chapterIndex}|${it.taskId}" to it.isCompleted }
        }
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyMap())

    init {
        // Observe user license to restore track and check demo status
        viewModelScope.launch(Dispatchers.IO) {
            userLicense.collect { user ->
                if (user != null) {
                    if (!user.isActive) {
                        _authError.value = "🚫 আপনার এক্সেস অ্যাডমিন প্যানেল থেকে ব্লক করা হয়েছে। অনুগ্রহ করে অ্যাডমিনের সাথে যোগাযোগ করুন।"
                        _showAuthScreen.value = true
                        _isDemoActive.value = false
                        demoTimerJob?.cancel()
                        return@collect
                    }
                    if (System.currentTimeMillis() > user.expiresAt) {
                        _authError.value = "⚠️ আপনার ক্লাউড এক্সেসের মেয়াদ শেষ হয়েছে! রিনিউ করতে হোয়াটসঅ্যাপে (01727328822) যোগাযোগ করুন।"
                        _showAuthScreen.value = true
                        _isDemoActive.value = false
                        demoTimerJob?.cancel()
                        return@collect
                    }

                    // Enforce track assignment
                    val allowed = if (user.accessCode == "DEMO-10MIN" || user.selectedTrack == "all") {
                        PrepTrack.entries
                    } else {
                        val assignedIds = user.selectedTrack.split(",")
                            .map { it.trim().lowercase(Locale.ROOT) }
                            .filter { it.isNotEmpty() }
                        val matched = PrepTrack.entries.filter { assignedIds.contains(it.id.lowercase(Locale.ROOT)) }
                        if (matched.isNotEmpty()) matched else listOf(PrepTrack.VARSITY_A)
                    }

                    if (!allowed.contains(_selectedTrack.value)) {
                        _selectedTrack.value = allowed.first()
                    }
                    _showAuthScreen.value = false

                    if (user.accessCode == "DEMO-10MIN") {
                        _isDemoActive.value = true
                        startDemoCountdown()
                    } else {
                        _isDemoActive.value = false
                        demoTimerJob?.cancel()
                    }
                } else {
                    _showAuthScreen.value = true
                    _isDemoActive.value = false
                    demoTimerJob?.cancel()
                }
            }
        }
    }

    private fun startDemoCountdown() {
        demoTimerJob?.cancel()
        demoTimerJob = viewModelScope.launch(Dispatchers.IO) {
            while (true) {
                val remainingMs = repository.getDemoRemainingMillis()
                if (remainingMs <= 0) {
                    _demoRemainingSeconds.value = 0
                    _isDemoActive.value = false
                    repository.finishAndExpireDemo()
                    _authError.value = "⚠️ আপনার ডিভাইসে ১০ মিনিটের ফ্রি ডেমো ট্রায়ালের মেয়াদ শেষ হয়েছে! ফুল এক্সেস পেতে হোয়াটসঅ্যাপে (01727328822) মেসেজ দিন।"
                    _showAuthScreen.value = true
                    break
                }
                _demoRemainingSeconds.value = remainingMs / 1000
                delay(1000)
            }
        }
    }

    fun toggleTheme() {
        _isDarkTheme.value = !_isDarkTheme.value
    }

    fun toggleStudyAlert() {
        _isStudyAlertEnabled.value = !_isStudyAlertEnabled.value
    }

    fun getAllowedTracks(): List<PrepTrack> {
        val user = userLicense.value ?: return PrepTrack.entries
        if (user.accessCode == "DEMO-10MIN" || user.selectedTrack == "all") {
            return PrepTrack.entries
        }
        val assignedIds = user.selectedTrack.split(",")
            .map { it.trim().lowercase(Locale.ROOT) }
            .filter { it.isNotEmpty() }
        val matched = PrepTrack.entries.filter { assignedIds.contains(it.id.lowercase(Locale.ROOT)) }
        return if (matched.isNotEmpty()) matched else listOf(PrepTrack.VARSITY_A)
    }

    fun switchTrack(track: PrepTrack) {
        val allowed = getAllowedTracks()
        if (!allowed.contains(track)) return
        _selectedTrack.value = track
        _currentView.value = "home"
        _expandedSubjects.value = emptySet()
        _expandedChapters.value = emptySet()
    }

    fun switchView(view: String) {
        _currentView.value = view
    }

    fun toggleSubjectExpanded(subjectName: String) {
        val current = _expandedSubjects.value
        _expandedSubjects.value = if (current.contains(subjectName)) {
            current - subjectName
        } else {
            current + subjectName
        }
    }

    fun toggleChapterExpanded(chapterKey: String) {
        val current = _expandedChapters.value
        _expandedChapters.value = if (current.contains(chapterKey)) {
            current - chapterKey
        } else {
            current + chapterKey
        }
    }

    fun updateSearchQuery(query: String) {
        _searchQuery.value = query
    }

    fun toggleTask(subject: Subject, chapterIndex: Int, taskId: String) {
        val trackId = _selectedTrack.value.id
        val key = "$trackId|${subject.name}|$chapterIndex|$taskId"
        val currentStatus = progressMap.value[key] ?: false
        viewModelScope.launch(Dispatchers.IO) {
            repository.toggleTask(trackId, subject.name, chapterIndex, taskId, !currentStatus)
        }
    }

    fun getChapterProgress(subject: Subject, chapterIndex: Int): Int {
        if (chapterIndex !in subject.chapters.indices) return 0
        val chapter = subject.chapters[chapterIndex]
        val tasks = chapter.getEffectiveTasks()
        val trackId = _selectedTrack.value.id
        var score = 0
        tasks.forEach { task ->
            val key = "$trackId|${subject.name}|$chapterIndex|${task.id}"
            if (progressMap.value[key] == true) {
                score += task.weight
            }
        }
        return score.coerceIn(0, 100)
    }

    fun getSubjectProgress(subject: Subject): Int {
        if (subject.chapters.isEmpty()) return 0
        var total = 0
        subject.chapters.indices.forEach { idx ->
            total += getChapterProgress(subject, idx)
        }
        return (total / subject.chapters.size).coerceIn(0, 100)
    }

    fun getModuleProgress(module: ModuleSection): Int {
        if (module.subjects.isEmpty()) return 0
        var total = 0
        module.subjects.forEach { subject ->
            total += getSubjectProgress(subject)
        }
        return (total / module.subjects.size).coerceIn(0, 100)
    }

    fun getModulesForTrack(track: PrepTrack): List<ModuleSection> {
        return currentModules.value.ifEmpty {
            val baseModules = SyllabusData.getModulesForTrack(track)
            baseModules.map { module ->
                val subjects = repository.getSubjects(track.id, module.id, module.subjects)
                module.copy(subjects = subjects)
            }
        }
    }

    fun getOverallTrackProgress(): Int {
        val modules = getModulesForTrack(_selectedTrack.value)
        val allSubjects = modules.flatMap { it.subjects }
        if (allSubjects.isEmpty()) return 0
        var total = 0
        allSubjects.forEach { subject ->
            total += getSubjectProgress(subject)
        }
        return (total / allSubjects.size).coerceIn(0, 100)
    }

    // --- Subject Management ---
    fun addSubjectToModule(
        moduleId: String,
        name: String,
        category: String,
        iconEmoji: String
    ) {
        val cleanName = name.trim()
        if (cleanName.isBlank()) return
        val track = _selectedTrack.value
        val baseModule = SyllabusData.getModulesForTrack(track).find { it.id == moduleId } ?: return
        val newSubject = Subject(
            name = cleanName,
            category = if (category.isBlank()) "General" else category.trim(),
            colorStartHex = 0xFF6366F1,
            colorEndHex = 0xFF8B5CF6,
            iconEmoji = if (iconEmoji.isBlank()) "📚" else iconEmoji.trim(),
            chapters = listOf(
                Chapter("১. প্রথম অধ্যায় / সূচনা ও বেসিক কনসেপ্ট", com.example.model.TaskSetType.STANDARD)
            )
        )
        viewModelScope.launch(Dispatchers.IO) {
            repository.addSubject(track.id, moduleId, newSubject, baseModule.subjects)
            _syllabusVersion.value = System.currentTimeMillis()
        }
    }

    fun editSubjectInModule(
        moduleId: String,
        subjectIndex: Int,
        newName: String,
        category: String,
        iconEmoji: String
    ) {
        val cleanName = newName.trim()
        if (cleanName.isBlank()) return
        val track = _selectedTrack.value
        val baseModule = SyllabusData.getModulesForTrack(track).find { it.id == moduleId } ?: return
        viewModelScope.launch(Dispatchers.IO) {
            val currentSubjects = repository.getSubjects(track.id, moduleId, baseModule.subjects)
            val existing = currentSubjects.getOrNull(subjectIndex) ?: return@launch
            val updated = existing.copy(
                name = cleanName,
                category = if (category.isBlank()) existing.category else category.trim(),
                iconEmoji = if (iconEmoji.isBlank()) existing.iconEmoji else iconEmoji.trim()
            )
            repository.editSubject(track.id, moduleId, subjectIndex, updated, baseModule.subjects)
            _syllabusVersion.value = System.currentTimeMillis()
        }
    }

    fun deleteSubjectFromModule(moduleId: String, subjectIndex: Int) {
        val track = _selectedTrack.value
        val baseModule = SyllabusData.getModulesForTrack(track).find { it.id == moduleId } ?: return
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteSubject(track.id, moduleId, subjectIndex, baseModule.subjects)
            _syllabusVersion.value = System.currentTimeMillis()
        }
    }

    fun restoreModuleSubjects(moduleId: String) {
        val track = _selectedTrack.value
        viewModelScope.launch(Dispatchers.IO) {
            repository.restoreDefaultSubjects(track.id, moduleId)
            _syllabusVersion.value = System.currentTimeMillis()
        }
    }

    fun hasCustomSubjects(moduleId: String): Boolean {
        val track = _selectedTrack.value
        return repository.hasCustomSubjects(track.id, moduleId)
    }

    // --- Chapter / Topic Management ---
    fun addChapterToSubject(
        subject: Subject,
        title: String,
        driveLink: String = "",
        taskType: com.example.model.TaskSetType = subject.chapters.firstOrNull()?.taskType ?: com.example.model.TaskSetType.STANDARD,
        customTasks: List<com.example.model.StudyTask>? = null
    ) {
        val cleanTitle = title.trim()
        if (cleanTitle.isBlank()) return
        val trackId = _selectedTrack.value.id
        val newChapter = Chapter(
            title = cleanTitle,
            taskType = taskType,
            driveLink = driveLink.trim(),
            customTasks = customTasks
        )
        viewModelScope.launch(Dispatchers.IO) {
            repository.addChapter(trackId, subject.name, newChapter, subject.chapters)
            _syllabusVersion.value = System.currentTimeMillis()
        }
    }

    fun editChapterInSubject(
        subject: Subject,
        chapterIndex: Int,
        title: String,
        driveLink: String = "",
        customTasks: List<com.example.model.StudyTask>? = null
    ) {
        val cleanTitle = title.trim()
        if (cleanTitle.isBlank()) return
        val trackId = _selectedTrack.value.id
        viewModelScope.launch(Dispatchers.IO) {
            val existingChapter = subject.chapters.getOrNull(chapterIndex) ?: return@launch
            val updated = existingChapter.copy(
                title = cleanTitle,
                driveLink = driveLink.trim(),
                customTasks = customTasks
            )
            repository.editChapter(trackId, subject.name, chapterIndex, updated, subject.chapters)
            _syllabusVersion.value = System.currentTimeMillis()
        }
    }

    fun deleteChapterFromSubject(subject: Subject, chapterIndex: Int) {
        val trackId = _selectedTrack.value.id
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteChapter(trackId, subject.name, chapterIndex, subject.chapters)
            _syllabusVersion.value = System.currentTimeMillis()
        }
    }

    fun restoreSubjectChapters(subject: Subject) {
        val trackId = _selectedTrack.value.id
        viewModelScope.launch(Dispatchers.IO) {
            repository.restoreDefaultChapters(trackId, subject.name)
            _syllabusVersion.value = System.currentTimeMillis()
        }
    }

    fun hasCustomChapters(subject: Subject): Boolean {
        val trackId = _selectedTrack.value.id
        return repository.hasCustomChapters(trackId, subject.name)
    }

    // --- Auth & Admin Operations ---
    fun login(email: String, code: String) {
        val cleanEmail = email.trim().lowercase(Locale.ROOT)
        val cleanCode = code.trim().uppercase(Locale.ROOT)

        if (cleanEmail.isBlank() || cleanCode.isBlank()) {
            _authError.value = "⚠️ জিমেইল এবং এক্সেস কোড দুটোই পূরণ করুন!"
            return
        }

        if (!cleanEmail.contains("@") || !cleanEmail.contains(".")) {
            _authError.value = "⚠️ সঠিক জিমেইল অ্যাড্রেস প্রদান করুন!"
            return
        }

        viewModelScope.launch(Dispatchers.IO) {
            val result = repository.loginWithCredentials(cleanEmail, cleanCode)
            if (result.isSuccess) {
                _authError.value = null
                _showAuthScreen.value = false
            } else {
                _authError.value = result.exceptionOrNull()?.message 
                    ?: "❌ ভুল অ্যাক্সেস কোড বা জিমেইল! সঠিক তথ্য দিয়ে আবার চেষ্টা করুন।"
            }
        }
    }

    fun start10MinuteDemo() {
        viewModelScope.launch(Dispatchers.IO) {
            val result = repository.startDemoTrial()
            if (result.isSuccess) {
                _authError.value = null
                _showAuthScreen.value = false
            } else {
                _authError.value = result.exceptionOrNull()?.message ?: "Demo could not be started."
            }
        }
    }

    fun openAdminModal() {
        _showAdminModal.value = true
        _isAdminUnlocked.value = false
        _adminMessage.value = null
        _generatedAdminCode.value = null
    }

    fun closeAdminModal() {
        _showAdminModal.value = false
    }

    fun verifyAdminPasscode(passcode: String): Boolean {
        val isValid = passcode.trim() == "@RIMITSTUDY123@"
        _isAdminUnlocked.value = isValid
        if (!isValid) {
            _adminMessage.value = "❌ ভুল অ্যাডমিন পাসকোড!"
        } else {
            _adminMessage.value = null
        }
        return isValid
    }

    fun generateAdminToken(email: String, days: Int, trackId: String = "varsity_a") {
        if (email.isBlank() || !email.contains("@")) {
            _adminMessage.value = "⚠️ অনুগ্রহ করে শিক্ষার্থীর বৈধ জিমেইল দিন।"
            return
        }
        viewModelScope.launch(Dispatchers.IO) {
            val code = repository.createOrRenewCode(email, days, trackId)
            _generatedAdminCode.value = code
            val trackSummary = formatTrackSummary(trackId)
            _adminMessage.value = "✅ কোড তৈরি হয়েছে $email এর জন্য! প্রোগ্রাম: $trackSummary ($days দিন মেয়াদ)"
        }
    }

    private fun formatTrackSummary(trackId: String): String {
        if (trackId == "all") return "All Programs (Full VIP)"
        val ids = trackId.split(",").map { it.trim() }.filter { it.isNotEmpty() }
        val names = ids.mapNotNull { id ->
            PrepTrack.entries.find { it.id.equals(id, ignoreCase = true) }?.shortName
        }
        return if (names.isNotEmpty()) names.joinToString(" + ") else trackId
    }

    fun revokeClientAccess(email: String) {
        if (email.isBlank()) return
        viewModelScope.launch(Dispatchers.IO) {
            repository.revokeAccess(email)
            _adminMessage.value = "🚫 $email এর এক্সেস অবিলম্বে ব্লক করা হয়েছে।"
        }
    }

    fun unblockClientAccess(email: String) {
        if (email.isBlank()) return
        viewModelScope.launch(Dispatchers.IO) {
            repository.unblockAccess(email)
            _adminMessage.value = "✅ $email এর এক্সেস সফলভাবে আনব্লক করা হয়েছে।"
        }
    }

    fun deleteClientLicense(email: String) {
        if (email.isBlank()) return
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteLicense(email)
            _adminMessage.value = "🗑️ $email এর লাইসেন্স রেকর্ড মুছে ফেলা হয়েছে।"
        }
    }

    fun logout() {
        viewModelScope.launch(Dispatchers.IO) {
            repository.logout()
            _showAuthScreen.value = true
        }
    }
}
