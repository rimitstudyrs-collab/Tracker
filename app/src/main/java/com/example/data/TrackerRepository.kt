package com.example.data

import android.content.Context
import android.content.SharedPreferences
import com.example.model.Chapter
import com.example.model.Subject
import com.example.model.TaskSetType
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map
import org.json.JSONArray
import org.json.JSONObject
import java.util.Locale
import java.util.UUID

class TrackerRepository(
    private val trackerDao: TrackerDao,
    context: Context
) {
    private val prefs: SharedPreferences = context.getSharedPreferences("hsc_tracker_prefs", Context.MODE_PRIVATE)

    companion object {
        private const val KEY_CURRENT_SESSION_EMAIL = "key_current_session_email"
        private const val KEY_DEMO_EXPIRED = "key_demo_expired"
        private const val KEY_DEMO_EXPIRES_AT = "key_demo_expires_at"
        const val DEMO_DURATION_MILLIS = 10 * 60 * 1000L // 10 Minutes
        const val WHATSAPP_NUMBER = "01727328822"
        const val WHATSAPP_FULL_NUMBER = "8801727328822"
    }

    private val currentSessionEmailFlow = MutableStateFlow<String?>(
        prefs.getString(KEY_CURRENT_SESSION_EMAIL, null)
    )

    val allLicenses: Flow<List<UserLicenseEntity>> = trackerDao.getAllLicenses()

    val userLicense: Flow<UserLicenseEntity?> = trackerDao.getAllLicenses().map { list ->
        val activeEmail = currentSessionEmailFlow.value ?: prefs.getString(KEY_CURRENT_SESSION_EMAIL, null)
        if (!activeEmail.isNullOrBlank()) {
            list.find { it.email.equals(activeEmail, ignoreCase = true) }
        } else {
            null
        }
    }

    fun getProgressForTrack(trackId: String): Flow<List<TaskProgressEntity>> {
        return trackerDao.getProgressForTrack(trackId)
    }

    suspend fun toggleTask(
        trackId: String,
        subjectName: String,
        chapterIndex: Int,
        taskId: String,
        newStatus: Boolean
    ) {
        trackerDao.insertOrUpdateTask(
            TaskProgressEntity(
                trackId = trackId,
                subjectName = subjectName,
                chapterIndex = chapterIndex,
                taskId = taskId,
                isCompleted = newStatus,
                updatedAt = System.currentTimeMillis()
            )
        )
    }

    fun isDemoExpired(): Boolean {
        return prefs.getBoolean(KEY_DEMO_EXPIRED, false)
    }

    fun getDemoRemainingMillis(): Long {
        val expiresAt = prefs.getLong(KEY_DEMO_EXPIRES_AT, 0L)
        if (expiresAt == 0L) return 0L
        val remaining = expiresAt - System.currentTimeMillis()
        if (remaining <= 0) {
            prefs.edit().putBoolean(KEY_DEMO_EXPIRED, true).apply()
            return 0L
        }
        return remaining
    }

    suspend fun startDemoTrial(): Result<UserLicenseEntity> {
        if (isDemoExpired()) {
            return Result.failure(
                IllegalStateException("⚠️ আপনার ফোনে ১০ মিনিটের ফ্রি ডেমো ট্রায়াল ইতিমধ্যে শেষ হয়েছে! ফুল এক্সেস পেতে হোয়াটসঅ্যাপে (01727328822) মেসেজ দিন।")
            )
        }

        // Wipe any previous saved progress on demo start as requested
        trackerDao.clearAllProgress()

        val expiresAt = System.currentTimeMillis() + DEMO_DURATION_MILLIS
        prefs.edit()
            .putLong(KEY_DEMO_EXPIRES_AT, expiresAt)
            .apply()

        val demoUser = UserLicenseEntity(
            email = "demo.student@gmail.com",
            accessCode = "DEMO-10MIN",
            expiresAt = expiresAt,
            isActive = true,
            selectedTrack = "all",
            lastActiveAt = System.currentTimeMillis()
        )
        trackerDao.insertUserLicense(demoUser)
        prefs.edit().putString(KEY_CURRENT_SESSION_EMAIL, demoUser.email).apply()
        currentSessionEmailFlow.value = demoUser.email
        return Result.success(demoUser)
    }

    suspend fun finishAndExpireDemo() {
        prefs.edit().putBoolean(KEY_DEMO_EXPIRED, true).apply()
        prefs.edit().remove(KEY_CURRENT_SESSION_EMAIL).apply()
        currentSessionEmailFlow.value = null
        trackerDao.clearAllProgress()
    }

    suspend fun loginWithCredentials(email: String, code: String): Result<UserLicenseEntity> {
        val sanitizedEmail = email.trim().lowercase(Locale.ROOT)
        val sanitizedCode = code.trim().uppercase(Locale.ROOT)

        if (sanitizedEmail.isBlank() || !sanitizedEmail.contains("@")) {
            return Result.failure(IllegalArgumentException("অনুগ্রহ করে বৈধ শিক্ষার্থী জিমেইল দিন।"))
        }
        if (sanitizedCode.isBlank()) {
            return Result.failure(IllegalArgumentException("অনুগ্রহ করে আপনার এক্সেস কোড দিন।"))
        }

        // If trying demo code manually after expiration
        if (sanitizedCode.startsWith("DEMO") && isDemoExpired()) {
            return Result.failure(IllegalAccessException("⚠️ ১০ মিনিটের ফ্রি ডেমো মেয়াদ শেষ। ফুল এক্সেস কোড নিতে হোয়াটসঅ্যাপে (01727328822) মেসেজ দিন।"))
        }

        // Check if user exists in database
        val existing = trackerDao.getLicenseByEmail(sanitizedEmail)
        if (existing != null) {
            if (existing.accessCode != sanitizedCode) {
                return Result.failure(IllegalAccessException("❌ এই জিমেইলের জন্য দেওয়া এক্সেস কোডটি সঠিক নয়!"))
            }
            if (!existing.isActive) {
                return Result.failure(IllegalAccessException("🚫 আপনার এক্সেস অ্যাডমিন প্যানেল থেকে ব্লক করা হয়েছে। অ্যাডমিনের সাথে যোগাযোগ করুন।"))
            }
            if (System.currentTimeMillis() > existing.expiresAt) {
                return Result.failure(IllegalAccessException("⚠️ আপনার ক্লাউড এক্সেসের মেয়াদ শেষ হয়েছে! রিনিউ করতে হোয়াটসঅ্যাপে যোগাযোগ করুন: 01727328822"))
            }
            val updated = existing.copy(lastActiveAt = System.currentTimeMillis())
            trackerDao.insertUserLicense(updated)
            prefs.edit().putString(KEY_CURRENT_SESSION_EMAIL, sanitizedEmail).apply()
            currentSessionEmailFlow.value = sanitizedEmail
            return Result.success(updated)
        } else {
            // Check code prefix to determine track assignment
            val assignedTrack = when {
                sanitizedCode.startsWith("VARA") -> "varsity_a"
                sanitizedCode.startsWith("VARB") -> "varsity_b"
                sanitizedCode.startsWith("VARC") -> "varsity_c"
                sanitizedCode.startsWith("VARD") -> "varsity_d"
                sanitizedCode.startsWith("VARSITY") -> "varsity_a"
                sanitizedCode.startsWith("MED") -> "medical"
                sanitizedCode.startsWith("ENGG") -> "engineering"
                sanitizedCode.startsWith("VIP") -> "all"
                sanitizedCode.startsWith("HSC") -> "hsc"
                else -> "varsity_a"
            }
            val expiresAt = System.currentTimeMillis() + (365L * 24 * 60 * 60 * 1000)
            val newLicense = UserLicenseEntity(
                email = sanitizedEmail,
                accessCode = sanitizedCode,
                expiresAt = expiresAt,
                isActive = true,
                selectedTrack = assignedTrack,
                lastActiveAt = System.currentTimeMillis()
            )
            trackerDao.insertUserLicense(newLicense)
            prefs.edit().putString(KEY_CURRENT_SESSION_EMAIL, sanitizedEmail).apply()
            currentSessionEmailFlow.value = sanitizedEmail
            return Result.success(newLicense)
        }
    }

    suspend fun createOrRenewCode(email: String, days: Int, trackId: String = "varsity_a"): String {
        val sanitizedEmail = email.trim().lowercase(Locale.ROOT)
        val prefix = when {
            trackId == "all" -> "VIP"
            trackId.contains(",") -> "COMBO"
            trackId == "varsity_a" -> "VARA"
            trackId == "varsity_b" -> "VARB"
            trackId == "varsity_c" -> "VARC"
            trackId == "varsity_d" -> "VARD"
            trackId == "medical" -> "MED"
            trackId == "engineering" -> "ENGG"
            trackId == "hsc" -> "HSC"
            else -> "CODE"
        }
        val randomSuffix = UUID.randomUUID().toString().substring(0, 6).uppercase(Locale.ROOT)
        val newCode = "$prefix-$randomSuffix"
        val expiresAt = System.currentTimeMillis() + (days.toLong() * 24 * 60 * 60 * 1000)

        val entity = UserLicenseEntity(
            email = sanitizedEmail,
            accessCode = newCode,
            expiresAt = expiresAt,
            isActive = true,
            selectedTrack = trackId,
            lastActiveAt = System.currentTimeMillis()
        )
        trackerDao.insertUserLicense(entity)
        return newCode
    }

    suspend fun revokeAccess(email: String) {
        val sanitizedEmail = email.trim().lowercase(Locale.ROOT)
        trackerDao.updateUserStatus(sanitizedEmail, false)
        if (currentSessionEmailFlow.value.equals(sanitizedEmail, ignoreCase = true)) {
            prefs.edit().remove(KEY_CURRENT_SESSION_EMAIL).apply()
            currentSessionEmailFlow.value = null
        }
    }

    suspend fun unblockAccess(email: String) {
        val sanitizedEmail = email.trim().lowercase(Locale.ROOT)
        trackerDao.updateUserStatus(sanitizedEmail, true)
    }

    suspend fun deleteLicense(email: String) {
        val sanitizedEmail = email.trim().lowercase(Locale.ROOT)
        trackerDao.deleteLicense(sanitizedEmail)
        if (currentSessionEmailFlow.value.equals(sanitizedEmail, ignoreCase = true)) {
            prefs.edit().remove(KEY_CURRENT_SESSION_EMAIL).apply()
            currentSessionEmailFlow.value = null
        }
    }

    suspend fun updateTrack(email: String, trackId: String) {
        val sanitizedEmail = email.trim().lowercase(Locale.ROOT)
        trackerDao.updateSelectedTrack(sanitizedEmail, trackId)
    }

    suspend fun logout() {
        prefs.edit().remove(KEY_CURRENT_SESSION_EMAIL).apply()
        currentSessionEmailFlow.value = null
    }

    fun getChapters(trackId: String, subjectName: String, defaultChapters: List<Chapter>): List<Chapter> {
        val key = "custom_chps_${trackId}_${subjectName}"
        val jsonStr = prefs.getString(key, null) ?: return defaultChapters
        return try {
            val array = JSONArray(jsonStr)
            val list = mutableListOf<Chapter>()
            for (i in 0 until array.length()) {
                val obj = array.getJSONObject(i)
                val taskTypeStr = obj.optString("taskType", TaskSetType.STANDARD.name)
                val taskType = try {
                    TaskSetType.valueOf(taskTypeStr)
                } catch (e: Exception) {
                    TaskSetType.STANDARD
                }
                val customTasks = if (obj.has("customTasks")) {
                    val tArray = obj.getJSONArray("customTasks")
                    val tList = mutableListOf<com.example.model.StudyTask>()
                    for (t in 0 until tArray.length()) {
                        val tObj = tArray.getJSONObject(t)
                        tList.add(
                            com.example.model.StudyTask(
                                id = tObj.optString("id", "task_$t"),
                                label = tObj.optString("label", ""),
                                weight = tObj.optInt("weight", 0)
                            )
                        )
                    }
                    tList
                } else null

                list.add(
                    Chapter(
                        title = obj.getString("title"),
                        taskType = taskType,
                        driveLink = obj.optString("driveLink", ""),
                        extraNote = obj.optString("extraNote", ""),
                        customTasks = customTasks
                    )
                )
            }
            if (list.isEmpty()) defaultChapters else list
        } catch (e: Exception) {
            defaultChapters
        }
    }

    fun saveChapters(trackId: String, subjectName: String, chapters: List<Chapter>) {
        val key = "custom_chps_${trackId}_${subjectName}"
        val array = JSONArray()
        for (c in chapters) {
            val obj = JSONObject()
            obj.put("title", c.title)
            obj.put("taskType", c.taskType.name)
            obj.put("driveLink", c.driveLink)
            obj.put("extraNote", c.extraNote)
            if (c.customTasks != null) {
                val tArray = JSONArray()
                for (t in c.customTasks) {
                    val tObj = JSONObject()
                    tObj.put("id", t.id)
                    tObj.put("label", t.label)
                    tObj.put("weight", t.weight)
                    tArray.put(tObj)
                }
                obj.put("customTasks", tArray)
            }
            array.put(obj)
        }
        prefs.edit().putString(key, array.toString()).apply()
    }

    fun addChapter(trackId: String, subjectName: String, newChapter: Chapter, defaultChapters: List<Chapter>) {
        val current = getChapters(trackId, subjectName, defaultChapters).toMutableList()
        current.add(newChapter)
        saveChapters(trackId, subjectName, current)
    }

    fun editChapter(trackId: String, subjectName: String, index: Int, updatedChapter: Chapter, defaultChapters: List<Chapter>) {
        val current = getChapters(trackId, subjectName, defaultChapters).toMutableList()
        if (index in current.indices) {
            current[index] = updatedChapter
            saveChapters(trackId, subjectName, current)
        }
    }

    fun deleteChapter(trackId: String, subjectName: String, index: Int, defaultChapters: List<Chapter>) {
        val current = getChapters(trackId, subjectName, defaultChapters).toMutableList()
        if (index in current.indices) {
            current.removeAt(index)
            saveChapters(trackId, subjectName, current)
        }
    }

    fun restoreDefaultChapters(trackId: String, subjectName: String) {
        val key = "custom_chps_${trackId}_${subjectName}"
        prefs.edit().remove(key).apply()
    }

    fun hasCustomChapters(trackId: String, subjectName: String): Boolean {
        val key = "custom_chps_${trackId}_${subjectName}"
        return prefs.contains(key)
    }

    // --- Subject Management for Modules ---
    fun getSubjects(trackId: String, moduleId: String, defaultSubjects: List<Subject>): List<Subject> {
        val key = "custom_subs_${trackId}_${moduleId}"
        val jsonStr = prefs.getString(key, null)
        val loadedSubjects = if (jsonStr == null) {
            defaultSubjects
        } else {
            try {
                val array = JSONArray(jsonStr)
                val list = mutableListOf<Subject>()
                for (i in 0 until array.length()) {
                    val obj = array.getJSONObject(i)
                    val name = obj.getString("name")
                    val category = obj.optString("category", "General")
                    val colorStartHex = obj.optLong("colorStartHex", 0xFF6366F1)
                    val colorEndHex = obj.optLong("colorEndHex", 0xFF4F46E5)
                    val iconEmoji = obj.optString("iconEmoji", "📖")

                    val defaultChpsForSubject = defaultSubjects.find { it.name == name }?.chapters ?: emptyList()
                    val chpsArray = obj.optJSONArray("chapters")
                    val parsedChps = if (chpsArray != null && chpsArray.length() > 0) {
                        val cList = mutableListOf<Chapter>()
                        for (c in 0 until chpsArray.length()) {
                            val cObj = chpsArray.getJSONObject(c)
                            val taskTypeStr = cObj.optString("taskType", TaskSetType.STANDARD.name)
                            val taskType = try {
                                TaskSetType.valueOf(taskTypeStr)
                            } catch (e: Exception) {
                                TaskSetType.STANDARD
                            }
                            val customTasks = if (cObj.has("customTasks")) {
                                val tArray = cObj.getJSONArray("customTasks")
                                val tList = mutableListOf<com.example.model.StudyTask>()
                                for (t in 0 until tArray.length()) {
                                    val tObj = tArray.getJSONObject(t)
                                    tList.add(
                                        com.example.model.StudyTask(
                                            id = tObj.optString("id", "task_$t"),
                                            label = tObj.optString("label", ""),
                                            weight = tObj.optInt("weight", 0)
                                        )
                                    )
                                }
                                tList
                            } else null

                            cList.add(
                                Chapter(
                                    title = cObj.getString("title"),
                                    taskType = taskType,
                                    driveLink = cObj.optString("driveLink", ""),
                                    extraNote = cObj.optString("extraNote", ""),
                                    customTasks = customTasks
                                )
                            )
                        }
                        cList
                    } else {
                        defaultChpsForSubject
                    }

                    list.add(
                        Subject(
                            name = name,
                            category = category,
                            colorStartHex = colorStartHex,
                            colorEndHex = colorEndHex,
                            iconEmoji = iconEmoji,
                            chapters = parsedChps
                        )
                    )
                }
                if (list.isEmpty()) defaultSubjects else list
            } catch (e: Exception) {
                defaultSubjects
            }
        }

        // Apply any chapter customizations recorded for each subject
        return loadedSubjects.map { subject ->
            val chapters = getChapters(trackId, subject.name, subject.chapters)
            subject.copy(chapters = chapters)
        }
    }

    fun saveSubjects(trackId: String, moduleId: String, subjects: List<Subject>) {
        val key = "custom_subs_${trackId}_${moduleId}"
        val array = JSONArray()
        for (s in subjects) {
            val obj = JSONObject()
            obj.put("name", s.name)
            obj.put("category", s.category)
            obj.put("colorStartHex", s.colorStartHex)
            obj.put("colorEndHex", s.colorEndHex)
            obj.put("iconEmoji", s.iconEmoji)
            val chpsArray = JSONArray()
            for (c in s.chapters) {
                val cObj = JSONObject()
                cObj.put("title", c.title)
                cObj.put("taskType", c.taskType.name)
                cObj.put("driveLink", c.driveLink)
                cObj.put("extraNote", c.extraNote)
                if (c.customTasks != null) {
                    val tArray = JSONArray()
                    for (t in c.customTasks) {
                        val tObj = JSONObject()
                        tObj.put("id", t.id)
                        tObj.put("label", t.label)
                        tObj.put("weight", t.weight)
                        tArray.put(tObj)
                    }
                    cObj.put("customTasks", tArray)
                }
                chpsArray.put(cObj)
            }
            obj.put("chapters", chpsArray)
            array.put(obj)
        }
        prefs.edit().putString(key, array.toString()).apply()
    }

    fun addSubject(trackId: String, moduleId: String, newSubject: Subject, defaultSubjects: List<Subject>) {
        val current = getSubjects(trackId, moduleId, defaultSubjects).toMutableList()
        current.add(newSubject)
        saveSubjects(trackId, moduleId, current)
    }

    fun editSubject(trackId: String, moduleId: String, index: Int, updatedSubject: Subject, defaultSubjects: List<Subject>) {
        val current = getSubjects(trackId, moduleId, defaultSubjects).toMutableList()
        if (index in current.indices) {
            val oldSubject = current[index]
            if (oldSubject.name != updatedSubject.name) {
                val oldKey = "custom_chps_${trackId}_${oldSubject.name}"
                val oldData = prefs.getString(oldKey, null)
                if (oldData != null) {
                    prefs.edit().remove(oldKey).putString("custom_chps_${trackId}_${updatedSubject.name}", oldData).apply()
                }
            }
            current[index] = updatedSubject
            saveSubjects(trackId, moduleId, current)
        }
    }

    fun deleteSubject(trackId: String, moduleId: String, index: Int, defaultSubjects: List<Subject>) {
        val current = getSubjects(trackId, moduleId, defaultSubjects).toMutableList()
        if (index in current.indices) {
            val removed = current.removeAt(index)
            prefs.edit().remove("custom_chps_${trackId}_${removed.name}").apply()
            saveSubjects(trackId, moduleId, current)
        }
    }

    fun restoreDefaultSubjects(trackId: String, moduleId: String) {
        val key = "custom_subs_${trackId}_${moduleId}"
        prefs.edit().remove(key).apply()
    }

    fun hasCustomSubjects(trackId: String, moduleId: String): Boolean {
        val key = "custom_subs_${trackId}_${moduleId}"
        return prefs.contains(key)
    }
}
