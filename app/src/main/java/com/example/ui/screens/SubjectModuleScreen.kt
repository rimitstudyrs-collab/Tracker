package com.example.ui.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.DeleteOutline
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.OpenInNew
import androidx.compose.material.icons.filled.RestartAlt
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.model.Chapter
import com.example.model.ModuleSection
import com.example.model.StudyTask
import com.example.model.Subject
import com.example.model.TaskSetType
import com.example.model.TaskSets
import com.example.ui.TrackerViewModel
import com.example.ui.components.DriveLinkButton
import com.example.ui.components.ProgressBar
import com.example.ui.components.TopicAndTasksEditorDialog
import com.example.ui.components.launchUrl
import com.example.ui.theme.PremiumGold

@Composable
fun SubjectModuleScreen(
    module: ModuleSection,
    viewModel: TrackerViewModel,
    onBack: () -> Unit,
    modifier: Modifier = Modifier
) {
    val syllabusVersion by viewModel.syllabusVersion.collectAsState()
    val liveModules = viewModel.getModulesForTrack(viewModel.selectedTrack.value)
    val currentModule = liveModules.find { it.id == module.id } ?: module

    val expandedSubjects = viewModel.expandedSubjects.value
    val expandedChapters = viewModel.expandedChapters.value
    val progressMap = viewModel.progressMap.value

    val moduleProgress = remember(progressMap, currentModule) {
        viewModel.getModuleProgress(currentModule)
    }

    var showAddDialog by remember { mutableStateOf(false) }
    var subjectForAdd by remember { mutableStateOf<Subject?>(null) }
    var addChapterTitle by remember { mutableStateOf("") }
    var addChapterDriveLink by remember { mutableStateOf("") }

    var showEditDialog by remember { mutableStateOf(false) }
    var subjectForEdit by remember { mutableStateOf<Subject?>(null) }
    var chapterIndexForEdit by remember { mutableStateOf(-1) }
    var editChapterTitle by remember { mutableStateOf("") }
    var editChapterDriveLink by remember { mutableStateOf("") }

    var showDeleteDialog by remember { mutableStateOf(false) }
    var subjectForDelete by remember { mutableStateOf<Subject?>(null) }
    var chapterIndexForDelete by remember { mutableStateOf(-1) }
    var chapterTitleForDelete by remember { mutableStateOf("") }

    var showRestoreDialog by remember { mutableStateOf(false) }
    var subjectForRestore by remember { mutableStateOf<Subject?>(null) }

    var showAddSubjectDialog by remember { mutableStateOf(false) }
    var newSubjectName by remember { mutableStateOf("") }
    var newSubjectCategory by remember { mutableStateOf("") }
    var newSubjectEmoji by remember { mutableStateOf("📚") }

    var showEditSubjectDialog by remember { mutableStateOf(false) }
    var subjectIndexForEdit by remember { mutableStateOf(-1) }
    var editSubjectName by remember { mutableStateOf("") }
    var editSubjectCategory by remember { mutableStateOf("") }
    var editSubjectEmoji by remember { mutableStateOf("📚") }

    var showDeleteSubjectDialog by remember { mutableStateOf(false) }
    var subjectIndexForDelete by remember { mutableStateOf(-1) }
    var subjectTitleForDelete by remember { mutableStateOf("") }

    var showRestoreSubjectsDialog by remember { mutableStateOf(false) }

    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp),
        verticalArrangement = Arrangement.spacedBy(14.dp)
    ) {
        // Module Top Header Banner
        item {
            Spacer(modifier = Modifier.height(10.dp))
            Surface(
                modifier = Modifier
                    .fillMaxWidth()
                    .testTag("module_header_banner"),
                shape = RoundedCornerShape(24.dp),
                color = Color.Transparent
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(24.dp))
                        .background(
                            Brush.linearGradient(
                                listOf(
                                    Color(module.colorStartHex),
                                    Color(module.colorEndHex),
                                    Color(0xFF1E1E1E)
                                )
                            )
                        )
                        .border(
                            1.dp,
                            PremiumGold.copy(alpha = 0.35f),
                            RoundedCornerShape(24.dp)
                        )
                        .padding(18.dp)
                ) {
                    Column(modifier = Modifier.fillMaxWidth()) {
                        Row(
                            modifier = Modifier
                                .clickable { onBack() }
                                .padding(vertical = 2.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Icon(
                                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                                contentDescription = "Back",
                                tint = PremiumGold,
                                modifier = Modifier.size(16.dp)
                            )
                            Spacer(modifier = Modifier.width(4.dp))
                            Text(
                                text = "Back to Home",
                                fontSize = 12.sp,
                                fontWeight = FontWeight.Bold,
                                color = PremiumGold
                            )
                        }

                        Spacer(modifier = Modifier.height(10.dp))

                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Column(modifier = Modifier.weight(1f)) {
                                Text(
                                    text = "${module.iconEmoji} ${module.title}",
                                    fontSize = 20.sp,
                                    fontWeight = FontWeight.Black,
                                    color = Color.White
                                )
                                Text(
                                    text = module.subtitle,
                                    fontSize = 11.sp,
                                    color = Color.White.copy(alpha = 0.85f),
                                    modifier = Modifier.padding(top = 2.dp)
                                )
                            }

                            Box(
                                modifier = Modifier
                                    .clip(RoundedCornerShape(16.dp))
                                    .background(Color.Black.copy(alpha = 0.3f))
                                    .border(1.dp, PremiumGold.copy(alpha = 0.4f), RoundedCornerShape(16.dp))
                                    .padding(horizontal = 14.dp, vertical = 8.dp),
                                contentAlignment = Alignment.Center
                            ) {
                                Column(horizontalAlignment = Alignment.End) {
                                    Text(
                                        text = "PROGRESS",
                                        fontSize = 9.sp,
                                        fontWeight = FontWeight.Bold,
                                        color = PremiumGold
                                    )
                                    Text(
                                        text = "$moduleProgress%",
                                        fontSize = 24.sp,
                                        fontWeight = FontWeight.Black,
                                        color = Color.White
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }

        // Subjects List (Cards with accordions)
        itemsIndexed(
            items = currentModule.subjects,
            key = { _, subject -> subject.name }
        ) { subjectIdx, subject ->
            val isSubjectExpanded = expandedSubjects.contains(subject.name)
            
            // Cache performance calculation
            val subjectProgress = remember(progressMap, subject) {
                viewModel.getSubjectProgress(subject)
            }

            val subjectArrowAngle by animateFloatAsState(
                targetValue = if (isSubjectExpanded) 180f else 0f,
                label = "subjectArrow"
            )

            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .testTag("subject_card_${subject.name.replace(" ", "_")}"),
                shape = RoundedCornerShape(18.dp),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
                border = androidx.compose.foundation.BorderStroke(
                    1.dp,
                    if (isSubjectExpanded) PremiumGold.copy(alpha = 0.5f) else MaterialTheme.colorScheme.outline.copy(alpha = 0.3f)
                )
            ) {
                Column(modifier = Modifier.fillMaxWidth()) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable { viewModel.toggleSubjectExpanded(subject.name) }
                            .padding(horizontal = 14.dp, vertical = 12.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(10.dp),
                            modifier = Modifier.weight(1f)
                        ) {
                            Text(
                                text = subject.iconEmoji.ifBlank { "📚" },
                                fontSize = 22.sp
                            )
                            Column {
                                Text(
                                    text = subject.name,
                                    fontSize = 15.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = MaterialTheme.colorScheme.onSurface
                                )
                                Text(
                                    text = "${subject.chapters.size} Chapters • ${subject.category}",
                                    fontSize = 10.sp,
                                    color = MaterialTheme.colorScheme.onSurfaceVariant
                                )
                            }
                        }

                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(4.dp)
                        ) {
                            Box(
                                modifier = Modifier
                                    .clip(RoundedCornerShape(percent = 50))
                                    .background(MaterialTheme.colorScheme.surfaceVariant)
                                    .border(1.dp, PremiumGold.copy(alpha = 0.3f), CircleShape)
                                    .padding(horizontal = 7.dp, vertical = 2.dp)
                            ) {
                                Text(
                                    text = "$subjectProgress%",
                                    fontSize = 11.sp,
                                    fontWeight = FontWeight.Black,
                                    color = PremiumGold
                                )
                            }

                            IconButton(
                                onClick = {
                                    subjectIndexForEdit = subjectIdx
                                    editSubjectName = subject.name
                                    editSubjectCategory = subject.category
                                    editSubjectEmoji = subject.iconEmoji
                                    showEditSubjectDialog = true
                                },
                                modifier = Modifier.size(28.dp)
                            ) {
                                Icon(
                                    imageVector = Icons.Default.Edit,
                                    contentDescription = "Edit Subject",
                                    modifier = Modifier.size(15.dp),
                                    tint = PremiumGold
                                )
                            }

                            IconButton(
                                onClick = {
                                    subjectIndexForDelete = subjectIdx
                                    subjectTitleForDelete = subject.name
                                    showDeleteSubjectDialog = true
                                },
                                modifier = Modifier.size(28.dp)
                            ) {
                                Icon(
                                    imageVector = Icons.Default.DeleteOutline,
                                    contentDescription = "Delete Subject",
                                    modifier = Modifier.size(15.dp),
                                    tint = MaterialTheme.colorScheme.error.copy(alpha = 0.8f)
                                )
                            }

                            Icon(
                                imageVector = Icons.Default.KeyboardArrowDown,
                                contentDescription = "Expand",
                                modifier = Modifier
                                    .size(20.dp)
                                    .rotate(subjectArrowAngle),
                                tint = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                        }
                    }

                    ProgressBar(
                        progressPercent = subjectProgress,
                        height = 5,
                        brush = Brush.horizontalGradient(
                            listOf(
                                Color(subject.colorStartHex),
                                Color(subject.colorEndHex)
                            )
                        ),
                        modifier = Modifier.padding(horizontal = 16.dp, vertical = 2.dp)
                    )

                    AnimatedVisibility(
                        visible = isSubjectExpanded,
                        enter = fadeIn() + expandVertically(),
                        exit = fadeOut() + shrinkVertically()
                    ) {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(12.dp),
                            verticalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            subject.chapters.forEachIndexed { chapterIdx, chapter ->
                                ChapterAccordionItem(
                                    subject = subject,
                                    chapter = chapter,
                                    chapterIdx = chapterIdx,
                                    viewModel = viewModel,
                                    isExpanded = expandedChapters.contains("${subject.name}_$chapterIdx"),
                                    onEdit = {
                                        subjectForEdit = subject
                                        chapterIndexForEdit = chapterIdx
                                        editChapterTitle = chapter.title
                                        editChapterDriveLink = chapter.driveLink
                                        showEditDialog = true
                                    },
                                    onDelete = {
                                        subjectForDelete = subject
                                        chapterIndexForDelete = chapterIdx
                                        chapterTitleForDelete = chapter.title
                                        showDeleteDialog = true
                                    }
                                )
                            }

                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(top = 8.dp),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                OutlinedButton(
                                    onClick = {
                                        subjectForAdd = subject
                                        addChapterTitle = ""
                                        addChapterDriveLink = ""
                                        showAddDialog = true
                                    },
                                    shape = RoundedCornerShape(10.dp),
                                    colors = ButtonDefaults.outlinedButtonColors(
                                        contentColor = PremiumGold
                                    ),
                                    border = androidx.compose.foundation.BorderStroke(1.dp, PremiumGold.copy(alpha = 0.6f))
                                ) {
                                    Icon(
                                        imageVector = Icons.Default.Add,
                                        contentDescription = null,
                                        modifier = Modifier.size(16.dp)
                                    )
                                    Spacer(modifier = Modifier.width(6.dp))
                                    Text(
                                        text = "+ নতুন অধ্যায় / টপিক যুক্ত করুন",
                                        fontSize = 12.sp,
                                        fontWeight = FontWeight.Bold
                                    )
                                }

                                if (viewModel.hasCustomChapters(subject)) {
                                    TextButton(
                                        onClick = {
                                            subjectForRestore = subject
                                            showRestoreDialog = true
                                        }
                                    ) {
                                        Icon(
                                            imageVector = Icons.Default.RestartAlt,
                                            contentDescription = null,
                                            modifier = Modifier.size(14.dp),
                                            tint = MaterialTheme.colorScheme.error.copy(alpha = 0.8f)
                                        )
                                        Spacer(modifier = Modifier.width(4.dp))
                                        Text(
                                            text = "রিসেট",
                                            fontSize = 11.sp,
                                            fontWeight = FontWeight.SemiBold,
                                            color = MaterialTheme.colorScheme.error.copy(alpha = 0.8f)
                                        )
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }

        item {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 6.dp),
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.5f)),
                border = androidx.compose.foundation.BorderStroke(1.dp, PremiumGold.copy(alpha = 0.35f))
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(14.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(6.dp)
                ) {
                    Button(
                        onClick = {
                            newSubjectName = ""
                            newSubjectCategory = ""
                            newSubjectEmoji = "📚"
                            showAddSubjectDialog = true
                        },
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(12.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = PremiumGold,
                            contentColor = Color(0xFF121212)
                        )
                    ) {
                        Icon(
                            imageVector = Icons.Default.Add,
                            contentDescription = null,
                            modifier = Modifier.size(18.dp)
                        )
                        Spacer(modifier = Modifier.width(6.dp))
                        Text(
                            text = "+ নতুন বিষয় (Subject) যুক্ত করুন",
                            fontSize = 13.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }

                    if (viewModel.hasCustomSubjects(currentModule.id)) {
                        TextButton(
                            onClick = { showRestoreSubjectsDialog = true }
                        ) {
                            Icon(
                                imageVector = Icons.Default.RestartAlt,
                                contentDescription = null,
                                modifier = Modifier.size(15.dp),
                                tint = MaterialTheme.colorScheme.error.copy(alpha = 0.85f)
                            )
                            Spacer(modifier = Modifier.width(4.dp))
                            Text(
                                text = "এই মডিউলের মূল বিষয়াবলি ফিরিয়ে আনুন",
                                fontSize = 11.sp,
                                fontWeight = FontWeight.SemiBold,
                                color = MaterialTheme.colorScheme.error.copy(alpha = 0.85f)
                            )
                        }
                    }
                }
            }
        }

        item {
            Spacer(modifier = Modifier.height(28.dp))
        }
    }

    // Dialogs code remains unchanged
    if (showAddDialog && subjectForAdd != null) {
        val sub = subjectForAdd!!
        val defaultType = sub.chapters.firstOrNull()?.taskType ?: TaskSetType.STANDARD
        val defaultTasks = TaskSets.getTasks(defaultType)
        TopicAndTasksEditorDialog(
            dialogTitle = "নতুন অধ্যায় ও টাস্ক যোগ করুন",
            subjectName = sub.name,
            initialTitle = addChapterTitle,
            initialDriveLink = addChapterDriveLink,
            initialTasks = defaultTasks,
            defaultTasks = defaultTasks,
            onSave = { title, driveLink, tasks ->
                viewModel.addChapterToSubject(
                    subject = sub,
                    title = title,
                    driveLink = driveLink,
                    taskType = defaultType,
                    customTasks = tasks
                )
                showAddDialog = false
            },
            onDismiss = { showAddDialog = false }
        )
    }

    if (showEditDialog && subjectForEdit != null) {
        val sub = subjectForEdit!!
        val existingChapter = sub.chapters.getOrNull(chapterIndexForEdit)
        val defaultType = existingChapter?.taskType ?: TaskSetType.STANDARD
        val defaultTasks = TaskSets.getTasks(defaultType)
        val currentTasks = existingChapter?.getEffectiveTasks() ?: defaultTasks

        TopicAndTasksEditorDialog(
            dialogTitle = "অধ্যায় ও টাস্ক সম্পাদনা করুন",
            subjectName = sub.name,
            initialTitle = editChapterTitle,
            initialDriveLink = editChapterDriveLink,
            initialTasks = currentTasks,
            defaultTasks = defaultTasks,
            onSave = { title, driveLink, tasks ->
                viewModel.editChapterInSubject(
                    subject = sub,
                    chapterIndex = chapterIndexForEdit,
                    title = title,
                    driveLink = driveLink,
                    customTasks = tasks
                )
                showEditDialog = false
            },
            onDismiss = { showEditDialog = false }
        )
    }

    if (showDeleteDialog && subjectForDelete != null) {
        AlertDialog(
            onDismissRequest = { showDeleteDialog = false },
            title = {
                Text(
                    text = "অধ্যায় মুছে ফেলবেন?",
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.error
                )
            },
            text = {
                Text(
                    text = "আপনি কি নিশ্চিত যে \"$chapterTitleForDelete\" অধ্যায়টি মুছে ফেলতে চান?",
                    fontSize = 13.sp
                )
            },
            confirmButton = {
                Button(
                    onClick = {
                        val sub = subjectForDelete
                        if (sub != null && chapterIndexForDelete >= 0) {
                            viewModel.deleteChapterFromSubject(sub, chapterIndexForDelete)
                        }
                        showDeleteDialog = false
                    },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.error,
                        contentColor = Color.White
                    )
                ) {
                    Text("মুছে ফেলুন", fontWeight = FontWeight.Bold)
                }
            },
            dismissButton = {
                TextButton(onClick = { showDeleteDialog = false }) {
                    Text("বাতিল")
                }
            }
        )
    }

    if (showRestoreDialog && subjectForRestore != null) {
        AlertDialog(
            onDismissRequest = { showRestoreDialog = false },
            title = {
                Text(
                    text = "মূল সিলেবাস ফিরিয়ে আনবেন?",
                    fontWeight = FontWeight.Bold,
                    color = PremiumGold
                )
            },
            text = {
                Text(
                    text = "\"${subjectForRestore?.name}\" বিষয়ের সমস্ত কাস্টম সংযোজন ও পরিবর্তন মুছে মূল সিলেবাস ফিরিয়ে আনা হবে। আপনি কি নিশ্চিত?",
                    fontSize = 13.sp
                )
            },
            confirmButton = {
                Button(
                    onClick = {
                        val sub = subjectForRestore
                        if (sub != null) {
                            viewModel.restoreSubjectChapters(sub)
                        }
                        showRestoreDialog = false
                    },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = PremiumGold,
                        contentColor = Color(0xFF121212)
                    )
                ) {
                    Text("রিসেট করুন", fontWeight = FontWeight.Bold)
                }
            },
            dismissButton = {
                TextButton(onClick = { showRestoreDialog = false }) {
                    Text("বাতিল")
                }
            }
        )
    }

    if (showAddSubjectDialog) {
        val emojiList = listOf("📚", "🔬", "🌍", "🇧🇩", "📐", "🧪", "💡", "📝", "🏥", "💻")
        AlertDialog(
            onDismissRequest = { showAddSubjectDialog = false },
            title = {
                Text(
                    text = "নতুন বিষয় (Subject) যোগ করুন",
                    fontWeight = FontWeight.Bold,
                    color = PremiumGold
                )
            },
            text = {
                Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                    Text(
                        text = "মডিউল: ${currentModule.title}",
                        fontSize = 12.sp,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )

                    OutlinedTextField(
                        value = newSubjectName,
                        onValueChange = { newSubjectName = it },
                        label = { Text("বিষয়ের নাম") },
                        placeholder = { Text("যেমন: মেডিকেল জিকে: বাংলাদেশ বিষয়াবলী") },
                        modifier = Modifier.fillMaxWidth(),
                        singleLine = true
                    )

                    OutlinedTextField(
                        value = newSubjectCategory,
                        onValueChange = { newSubjectCategory = it },
                        label = { Text("ক্যাটাগরি বা বিষয়ভিত্তিক গ্রুপ") },
                        placeholder = { Text("যেমন: Medical GK বা General") },
                        modifier = Modifier.fillMaxWidth(),
                        singleLine = true
                    )

                    Column {
                        Text(
                            text = "আইকন ইমোজি নির্বাচন করুন:",
                            fontSize = 11.sp,
                            color = MaterialTheme.colorScheme.onSurfaceVariant,
                            modifier = Modifier.padding(bottom = 6.dp)
                        )
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.spacedBy(6.dp)
                        ) {
                            emojiList.take(5).forEach { emoji ->
                                Box(
                                    modifier = Modifier
                                        .size(36.dp)
                                        .clip(RoundedCornerShape(8.dp))
                                        .background(if (newSubjectEmoji == emoji) PremiumGold.copy(alpha = 0.3f) else MaterialTheme.colorScheme.surfaceVariant)
                                        .border(
                                            1.dp,
                                            if (newSubjectEmoji == emoji) PremiumGold else Color.Transparent,
                                            RoundedCornerShape(8.dp)
                                        )
                                        .clickable { newSubjectEmoji = emoji },
                                    contentAlignment = Alignment.Center
                                ) {
                                    Text(text = emoji, fontSize = 18.sp)
                                }
                            }
                        }
                        Spacer(modifier = Modifier.height(6.dp))
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.spacedBy(6.dp)
                        ) {
                            emojiList.drop(5).forEach { emoji ->
                                Box(
                                    modifier = Modifier
                                        .size(36.dp)
                                        .clip(RoundedCornerShape(8.dp))
                                        .background(if (newSubjectEmoji == emoji) PremiumGold.copy(alpha = 0.3f) else MaterialTheme.colorScheme.surfaceVariant)
                                        .border(
                                            1.dp,
                                            if (newSubjectEmoji == emoji) PremiumGold else Color.Transparent,
                                            RoundedCornerShape(8.dp)
                                        )
                                        .clickable { newSubjectEmoji = emoji },
                                    contentAlignment = Alignment.Center
                                ) {
                                    Text(text = emoji, fontSize = 18.sp)
                                }
                            }
                        }
                    }
                }
            },
            confirmButton = {
                Button(
                    onClick = {
                        if (newSubjectName.isNotBlank()) {
                            viewModel.addSubjectToModule(
                                moduleId = currentModule.id,
                                name = newSubjectName,
                                category = newSubjectCategory,
                                iconEmoji = newSubjectEmoji
                            )
                        }
                        showAddSubjectDialog = false
                    },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = PremiumGold,
                        contentColor = Color(0xFF121212)
                    )
                ) {
                    Text("বিষয় যুক্ত করুন", fontWeight = FontWeight.Bold)
                }
            },
            dismissButton = {
                TextButton(onClick = { showAddSubjectDialog = false }) {
                    Text("বাতিল")
                }
            }
        )
    }

    if (showEditSubjectDialog && subjectIndexForEdit >= 0) {
        val emojiList = listOf("📚", "🔬", "🌍", "🇧🇩", "📐", "🧪", "💡", "📝", "🏥", "💻")
        AlertDialog(
            onDismissRequest = { showEditSubjectDialog = false },
            title = {
                Text(
                    text = "বিষয় সম্পাদনা করুন",
                    fontWeight = FontWeight.Bold,
                    color = PremiumGold
                )
            },
            text = {
                Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                    OutlinedTextField(
                        value = editSubjectName,
                        onValueChange = { editSubjectName = it },
                        label = { Text("বিষয়ের নাম") },
                        modifier = Modifier.fillMaxWidth(),
                        singleLine = true
                    )

                    OutlinedTextField(
                        value = editSubjectCategory,
                        onValueChange = { editSubjectCategory = it },
                        label = { Text("ক্যাটাগরি") },
                        modifier = Modifier.fillMaxWidth(),
                        singleLine = true
                    )

                    Column {
                        Text(
                            text = "আইকন ইমোজি:",
                            fontSize = 11.sp,
                            color = MaterialTheme.colorScheme.onSurfaceVariant,
                            modifier = Modifier.padding(bottom = 6.dp)
                        )
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.spacedBy(6.dp)
                        ) {
                            emojiList.take(5).forEach { emoji ->
                                Box(
                                    modifier = Modifier
                                        .size(36.dp)
                                        .clip(RoundedCornerShape(8.dp))
                                        .background(if (editSubjectEmoji == emoji) PremiumGold.copy(alpha = 0.3f) else MaterialTheme.colorScheme.surfaceVariant)
                                        .border(
                                            1.dp,
                                            if (editSubjectEmoji == emoji) PremiumGold else Color.Transparent,
                                            RoundedCornerShape(8.dp)
                                        )
                                        .clickable { editSubjectEmoji = emoji },
                                    contentAlignment = Alignment.Center
                                ) {
                                    Text(text = emoji, fontSize = 18.sp)
                                }
                            }
                        }
                        Spacer(modifier = Modifier.height(6.dp))
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.spacedBy(6.dp)
                        ) {
                            emojiList.drop(5).forEach { emoji ->
                                Box(
                                    modifier = Modifier
                                        .size(36.dp)
                                        .clip(RoundedCornerShape(8.dp))
                                        .background(if (editSubjectEmoji == emoji) PremiumGold.copy(alpha = 0.3f) else MaterialTheme.colorScheme.surfaceVariant)
                                        .border(
                                            1.dp,
                                            if (editSubjectEmoji == emoji) PremiumGold else Color.Transparent,
                                            RoundedCornerShape(8.dp)
                                        )
                                        .clickable { editSubjectEmoji = emoji },
                                    contentAlignment = Alignment.Center
                                ) {
                                    Text(text = emoji, fontSize = 18.sp)
                                }
                            }
                        }
                    }
                }
            },
            confirmButton = {
                Button(
                    onClick = {
                        if (editSubjectName.isNotBlank() && subjectIndexForEdit >= 0) {
                            viewModel.editSubjectInModule(
                                moduleId = currentModule.id,
                                subjectIndex = subjectIndexForEdit,
                                newName = editSubjectName,
                                category = editSubjectCategory,
                                iconEmoji = editSubjectEmoji
                            )
                        }
                        showEditSubjectDialog = false
                    },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = PremiumGold,
                        contentColor = Color(0xFF121212)
                    )
                ) {
                    Text("সংরক্ষণ করুন", fontWeight = FontWeight.Bold)
                }
            },
            dismissButton = {
                TextButton(onClick = { showEditSubjectDialog = false }) {
                    Text("বাতিল")
                }
            }
        )
    }

    if (showDeleteSubjectDialog && subjectIndexForDelete >= 0) {
        AlertDialog(
            onDismissRequest = { showDeleteSubjectDialog = false },
            title = {
                Text(
                    text = "বিষয় মুছে ফেলবেন?",
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.error
                )
            },
            text = {
                Text(
                    text = "আপনি কি নিশ্চিত যে \"$subjectTitleForDelete\" বিষয়টি মুছে ফেলতে চান? এই বিষয়ের সব অধ্যায় ও প্রগ্রেস রেকর্ড মুছে যাবে।",
                    fontSize = 13.sp
                )
            },
            confirmButton = {
                Button(
                    onClick = {
                        if (subjectIndexForDelete >= 0) {
                            viewModel.deleteSubjectFromModule(currentModule.id, subjectIndexForDelete)
                        }
                        showDeleteSubjectDialog = false
                    },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.error,
                        contentColor = Color.White
                    )
                ) {
                    Text("মুছে ফেলুন", fontWeight = FontWeight.Bold)
                }
            },
            dismissButton = {
                TextButton(onClick = { showDeleteSubjectDialog = false }) {
                    Text("বাতিল")
                }
            }
        )
    }

    if (showRestoreSubjectsDialog) {
        AlertDialog(
            onDismissRequest = { showRestoreSubjectsDialog = false },
            title = {
                Text(
                    text = "মূল বিষয়াবলি ফিরিয়ে আনবেন?",
                    fontWeight = FontWeight.Bold,
                    color = PremiumGold
                )
            },
            text = {
                Text(
                    text = "এই মডিউলের (\"${currentModule.title}\") কাস্টম বিষয় সংযোজন ও পরিবর্তন মুছে ডিফল্ট বিষয়াবলি ফিরিয়ে আনা হবে। আপনি কি নিশ্চিত?",
                    fontSize = 13.sp
                )
            },
            confirmButton = {
                Button(
                    onClick = {
                        viewModel.restoreModuleSubjects(currentModule.id)
                        showRestoreSubjectsDialog = false
                    },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = PremiumGold,
                        contentColor = Color(0xFF121212)
                    )
                ) {
                    Text("রিসেট করুন", fontWeight = FontWeight.Bold)
                }
            },
            dismissButton = {
                TextButton(onClick = { showRestoreSubjectsDialog = false }) {
                    Text("বাতিল")
                }
            }
        )
    }
}

@Composable
fun ChapterAccordionItem(
    subject: Subject,
    chapter: Chapter,
    chapterIdx: Int,
    viewModel: TrackerViewModel,
    isExpanded: Boolean,
    onEdit: () -> Unit,
    onDelete: () -> Unit
) {
    val context = LocalContext.current
    val chapterKey = "${subject.name}_$chapterIdx"
    val tasks = chapter.getEffectiveTasks()
    val trackId = viewModel.selectedTrack.value.id
    val progressMap = viewModel.progressMap.value

    // Cache chapter progress computation per item
    val chapterProgress = remember(progressMap, subject, chapterIdx) {
        viewModel.getChapterProgress(subject, chapterIdx)
    }

    val arrowAngle by animateFloatAsState(
        targetValue = if (isExpanded) 180f else 0f,
        label = "chapterArrow"
    )

    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .testTag("chapter_item_${chapterIdx}"),
        shape = RoundedCornerShape(12.dp),
        color = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.5f),
        border = androidx.compose.foundation.BorderStroke(
            1.dp,
            MaterialTheme.colorScheme.outline.copy(alpha = 0.25f)
        )
    ) {
        Column(modifier = Modifier.fillMaxWidth()) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { viewModel.toggleChapterExpanded(chapterKey) }
                    .padding(10.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        text = chapter.title,
                        fontSize = 12.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = MaterialTheme.colorScheme.onSurface,
                        maxLines = 2,
                        overflow = TextOverflow.Ellipsis
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    ProgressBar(
                        progressPercent = chapterProgress,
                        height = 3,
                        brush = Brush.horizontalGradient(
                            listOf(
                                Color(subject.colorStartHex),
                                Color(subject.colorEndHex)
                            )
                        )
                    )
                }

                Spacer(modifier = Modifier.width(6.dp))

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    if (chapter.driveLink.isNotBlank()) {
                        DriveLinkButton(url = chapter.driveLink)
                    }

                    Text(
                        text = "$chapterProgress%",
                        fontSize = 11.sp,
                        fontWeight = FontWeight.Bold,
                        color = PremiumGold
                    )

                    IconButton(
                        onClick = onEdit,
                        modifier = Modifier.size(28.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Default.Edit,
                            contentDescription = "Edit chapter",
                            modifier = Modifier.size(15.dp),
                            tint = PremiumGold
                        )
                    }

                    IconButton(
                        onClick = onDelete,
                        modifier = Modifier.size(28.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Default.DeleteOutline,
                            contentDescription = "Delete chapter",
                            modifier = Modifier.size(15.dp),
                            tint = MaterialTheme.colorScheme.error.copy(alpha = 0.8f)
                        )
                    }

                    Icon(
                        imageVector = Icons.Default.KeyboardArrowDown,
                        contentDescription = "Expand chapter",
                        modifier = Modifier
                            .size(18.dp)
                            .rotate(arrowAngle),
                        tint = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }

            AnimatedVisibility(
                visible = isExpanded,
                enter = fadeIn() + expandVertically(),
                exit = fadeOut() + shrinkVertically()
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(MaterialTheme.colorScheme.surface)
                        .padding(12.dp)
                ) {
                    if (chapter.driveLink.isNotBlank()) {
                        Surface(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(bottom = 10.dp)
                                .clickable { launchUrl(context, chapter.driveLink) },
                            shape = RoundedCornerShape(10.dp),
                            color = PremiumGold.copy(alpha = 0.12f),
                            border = androidx.compose.foundation.BorderStroke(1.dp, PremiumGold.copy(alpha = 0.35f))
                        ) {
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(10.dp),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Text(
                                    text = "📄 Chapter Drive Resources Available",
                                    fontSize = 11.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = PremiumGold
                                )
                                Row(
                                    verticalAlignment = Alignment.CenterVertically,
                                    horizontalArrangement = Arrangement.spacedBy(4.dp)
                                ) {
                                    Text(
                                        text = "Open Link",
                                        fontSize = 11.sp,
                                        fontWeight = FontWeight.ExtraBold,
                                        color = PremiumGold
                                    )
                                    Icon(
                                        imageVector = Icons.Default.OpenInNew,
                                        contentDescription = null,
                                        modifier = Modifier.size(12.dp),
                                        tint = PremiumGold
                                    )
                                }
                            }
                        }
                    }

                    tasks.forEach { task ->
                        val taskKey = "$trackId|${subject.name}|$chapterIdx|${task.id}"
                        val isChecked = progressMap[taskKey] == true

                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .clip(RoundedCornerShape(8.dp))
                                .clickable {
                                    viewModel.toggleTask(subject, chapterIdx, task.id)
                                }
                                .padding(vertical = 4.dp, horizontal = 2.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Checkbox(
                                checked = isChecked,
                                onCheckedChange = {
                                    viewModel.toggleTask(subject, chapterIdx, task.id)
                                },
                                colors = CheckboxDefaults.colors(
                                    checkedColor = PremiumGold,
                                    checkmarkColor = Color(0xFF121212),
                                    uncheckedColor = MaterialTheme.colorScheme.outline
                                ),
                                modifier = Modifier.size(24.dp)
                            )

                            Spacer(modifier = Modifier.width(8.dp))

                            Text(
                                text = task.label,
                                fontSize = 12.sp,
                                fontWeight = if (isChecked) FontWeight.Normal else FontWeight.Medium,
                                textDecoration = if (isChecked) TextDecoration.LineThrough else TextDecoration.None,
                                color = if (isChecked) MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.6f) else MaterialTheme.colorScheme.onSurface,
                                modifier = Modifier.weight(1f)
                            )

                            Text(
                                text = "(${task.weight}%)",
                                fontSize = 10.sp,
                                color = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.7f)
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(6.dp))
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clip(RoundedCornerShape(8.dp))
                            .clickable { onEdit() }
                            .background(MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.6f))
                            .padding(horizontal = 10.dp, vertical = 6.dp),
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            imageVector = Icons.Default.Edit,
                            contentDescription = "Edit tasks",
                            tint = PremiumGold,
                            modifier = Modifier.size(13.dp)
                        )
                        Spacer(modifier = Modifier.width(6.dp))
                        Text(
                            text = "টাস্ক যোগ/বাদ ও পার্সেন্টেজ পরিবর্তন করুন",
                            fontSize = 11.sp,
                            fontWeight = FontWeight.SemiBold,
                            color = PremiumGold
                        )
                    }
                }
            }
        }
    }
}
