package com.example.ui.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.DeleteOutline
import androidx.compose.material.icons.filled.RestartAlt
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.example.model.StudyTask
import com.example.ui.theme.PremiumGold

data class EditableTaskItem(
    val id: String,
    val label: String,
    val weightText: String
) {
    val weight: Int
        get() = weightText.toIntOrNull() ?: 0
}

@Composable
fun TopicAndTasksEditorDialog(
    dialogTitle: String,
    subjectName: String,
    initialTitle: String,
    initialDriveLink: String,
    initialTasks: List<StudyTask>,
    defaultTasks: List<StudyTask>,
    onSave: (title: String, driveLink: String, tasks: List<StudyTask>) -> Unit,
    onDismiss: () -> Unit
) {
    var title by remember { mutableStateOf(initialTitle) }
    var driveLink by remember { mutableStateOf(initialDriveLink) }

    val baseTasks = if (initialTasks.isNotEmpty()) initialTasks else defaultTasks
    var tasks by remember {
        mutableStateOf(
            baseTasks.map {
                EditableTaskItem(
                    id = it.id,
                    label = it.label,
                    weightText = it.weight.toString()
                )
            }
        )
    }

    val totalWeight = tasks.sumOf { it.weight }
    val isHundred = totalWeight == 100
    val hasValidItems = tasks.isNotEmpty() && tasks.all { it.label.isNotBlank() && it.weight > 0 }
    val canSave = title.isNotBlank() && isHundred && hasValidItems

    val presets = listOf(
        "মূল বই লাইন-টু-লাইন রিডিং",
        "মৌলিক কনসেপ্ট ও লেকচার নোট",
        "বিগত বছরের প্রশ্নব্যাংক ড্রিল",
        "টাইপ-ভিত্তিক গাণিতিক প্র্যাকটিস",
        "সূত্র ও রিভিশন শর্ট নোট",
        "টাইমড মডেল টেস্ট ও মক এক্সাম",
        "ভুল উত্তর পর্যালোচনা ও দুর্বলতা সমাধান"
    )

    Dialog(
        onDismissRequest = onDismiss,
        properties = DialogProperties(usePlatformDefaultWidth = false)
    ) {
        Surface(
            modifier = Modifier
                .fillMaxWidth(0.95f)
                .heightIn(max = 700.dp)
                .padding(6.dp),
            shape = RoundedCornerShape(24.dp),
            color = MaterialTheme.colorScheme.surface,
            tonalElevation = 8.dp,
            border = BorderStroke(1.dp, MaterialTheme.colorScheme.outline.copy(alpha = 0.3f))
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(18.dp)
            ) {
                // Header
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column(modifier = Modifier.weight(1f)) {
                        Text(
                            text = dialogTitle,
                            fontSize = 17.sp,
                            fontWeight = FontWeight.Bold,
                            color = PremiumGold
                        )
                        Text(
                            text = "বিষয়: $subjectName",
                            fontSize = 12.sp,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                    IconButton(
                        onClick = onDismiss,
                        modifier = Modifier.size(32.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Default.Close,
                            contentDescription = "Close",
                            tint = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                }

                Spacer(modifier = Modifier.height(10.dp))
                HorizontalDivider(color = MaterialTheme.colorScheme.outline.copy(alpha = 0.2f))
                Spacer(modifier = Modifier.height(10.dp))

                // Scrollable Content
                Column(
                    modifier = Modifier
                        .weight(1f)
                        .verticalScroll(rememberScrollState())
                ) {
                    // Topic / Chapter Title
                    OutlinedTextField(
                        value = title,
                        onValueChange = { title = it },
                        label = { Text("অধ্যায় / টপিকের শিরোনাম") },
                        placeholder = { Text("যেমন: ভৌতজগৎ ও পরিমাপ") },
                        modifier = Modifier.fillMaxWidth(),
                        singleLine = false,
                        maxLines = 3,
                        shape = RoundedCornerShape(12.dp)
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    // Drive Link
                    OutlinedTextField(
                        value = driveLink,
                        onValueChange = { driveLink = it },
                        label = { Text("গুগল ড্রাইভ রিসোর্স লিংক (ঐচ্ছিক)") },
                        placeholder = { Text("https://drive.google.com/...") },
                        modifier = Modifier.fillMaxWidth(),
                        singleLine = true,
                        shape = RoundedCornerShape(12.dp)
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    // Task & Weight Customization Section Header
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "🎯 টাস্ক ও পার্সেন্টেজ (${tasks.size}টি)",
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.onSurface
                        )

                        // 100% Badge
                        Surface(
                            shape = RoundedCornerShape(12.dp),
                            color = if (isHundred) Color(0xFF10B981) else MaterialTheme.colorScheme.error
                        ) {
                            Row(
                                modifier = Modifier.padding(horizontal = 10.dp, vertical = 4.dp),
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.spacedBy(4.dp)
                            ) {
                                Icon(
                                    imageVector = if (isHundred) Icons.Default.CheckCircle else Icons.Default.Warning,
                                    contentDescription = null,
                                    tint = Color.White,
                                    modifier = Modifier.size(14.dp)
                                )
                                Text(
                                    text = if (isHundred) "১০০% পূর্ণ ✓" else "মোট: $totalWeight% / ১০০%",
                                    fontSize = 11.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = Color.White
                                )
                            }
                        }
                    }

                    Spacer(modifier = Modifier.height(6.dp))

                    // Percentage Condition Alert Banner
                    Surface(
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(12.dp),
                        color = if (isHundred) Color(0xFF10B981).copy(alpha = 0.12f) else MaterialTheme.colorScheme.error.copy(alpha = 0.12f),
                        border = BorderStroke(1.dp, if (isHundred) Color(0xFF10B981).copy(alpha = 0.4f) else MaterialTheme.colorScheme.error.copy(alpha = 0.4f))
                    ) {
                        Column(modifier = Modifier.padding(10.dp)) {
                            if (isHundred) {
                                Text(
                                    text = "✓ পার্সেন্টেজ নির্ভুল হয়েছে! সবগুলো টাস্কের যোগফল ঠিক ১০০%।",
                                    fontSize = 11.sp,
                                    fontWeight = FontWeight.Medium,
                                    color = Color(0xFF10B981)
                                )
                            } else {
                                val diff = 100 - totalWeight
                                Text(
                                    text = if (diff > 0) {
                                        "⚠️ সব টাস্কের পার্সেন্টেজের যোগফল অবশ্যই ১০০% হতে হবে! আরও $diff% যোগ করতে হবে।"
                                    } else {
                                        "⚠️ সব টাস্কের পার্সেন্টেজের যোগফল অবশ্যই ১০০% হতে হবে! ${-diff}% কমাতে হবে।"
                                    },
                                    fontSize = 11.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = MaterialTheme.colorScheme.error
                                )
                            }
                        }
                    }

                    Spacer(modifier = Modifier.height(10.dp))

                    // Preset Suggestions Row
                    Text(
                        text = "দ্রুত টাস্ক যুক্ত করতে ট্যাপ করুন:",
                        fontSize = 11.sp,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .horizontalScroll(rememberScrollState()),
                        horizontalArrangement = Arrangement.spacedBy(6.dp)
                    ) {
                        presets.forEach { presetName ->
                            Surface(
                                shape = RoundedCornerShape(8.dp),
                                color = PremiumGold.copy(alpha = 0.12f),
                                border = BorderStroke(1.dp, PremiumGold.copy(alpha = 0.4f)),
                                modifier = Modifier.clip(RoundedCornerShape(8.dp))
                            ) {
                                Row(
                                    modifier = Modifier
                                        .padding(horizontal = 8.dp, vertical = 5.dp),
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    TextButton(
                                        onClick = {
                                            val remaining = (100 - totalWeight).coerceAtLeast(0)
                                            val weightToAssign = if (remaining > 0) remaining else 10
                                            val newId = "task_${System.currentTimeMillis()}_${tasks.size}"
                                            tasks = tasks + EditableTaskItem(
                                                id = newId,
                                                label = presetName,
                                                weightText = weightToAssign.toString()
                                            )
                                        },
                                        contentPadding = androidx.compose.foundation.layout.PaddingValues(0.dp),
                                        modifier = Modifier.height(24.dp)
                                    ) {
                                        Text(
                                            text = "+ $presetName",
                                            fontSize = 11.sp,
                                            fontWeight = FontWeight.SemiBold,
                                            color = PremiumGold
                                        )
                                    }
                                }
                            }
                        }
                    }

                    Spacer(modifier = Modifier.height(12.dp))

                    // Task List Items
                    tasks.forEachIndexed { index, taskItem ->
                        Surface(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 3.dp),
                            shape = RoundedCornerShape(12.dp),
                            color = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.5f),
                            border = BorderStroke(1.dp, MaterialTheme.colorScheme.outline.copy(alpha = 0.2f))
                        ) {
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(horizontal = 8.dp, vertical = 6.dp),
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.spacedBy(6.dp)
                            ) {
                                // Index badge
                                Box(
                                    modifier = Modifier
                                        .size(24.dp)
                                        .clip(CircleShape)
                                        .background(MaterialTheme.colorScheme.outline.copy(alpha = 0.2f)),
                                    contentAlignment = Alignment.Center
                                ) {
                                    Text(
                                        text = "${index + 1}",
                                        fontSize = 11.sp,
                                        fontWeight = FontWeight.Bold,
                                        color = MaterialTheme.colorScheme.onSurface
                                    )
                                }

                                // Task Title Field
                                OutlinedTextField(
                                    value = taskItem.label,
                                    onValueChange = { newLabel ->
                                        tasks = tasks.mapIndexed { i, t ->
                                            if (i == index) t.copy(label = newLabel) else t
                                        }
                                    },
                                    placeholder = { Text("টাস্কের নাম", fontSize = 12.sp) },
                                    modifier = Modifier.weight(1f),
                                    singleLine = true,
                                    shape = RoundedCornerShape(8.dp)
                                )

                                // Percentage Field
                                OutlinedTextField(
                                    value = taskItem.weightText,
                                    onValueChange = { newWeight ->
                                        val digits = newWeight.filter { it.isDigit() }.take(3)
                                        tasks = tasks.mapIndexed { i, t ->
                                            if (i == index) t.copy(weightText = digits) else t
                                        }
                                    },
                                    trailingIcon = { Text("%", fontSize = 11.sp, fontWeight = FontWeight.Bold) },
                                    modifier = Modifier.width(76.dp),
                                    singleLine = true,
                                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                                    shape = RoundedCornerShape(8.dp)
                                )

                                // Delete Task Button
                                IconButton(
                                    onClick = {
                                        if (tasks.size > 1) {
                                            tasks = tasks.filterIndexed { i, _ -> i != index }
                                        }
                                    },
                                    enabled = tasks.size > 1,
                                    modifier = Modifier.size(32.dp)
                                ) {
                                    Icon(
                                        imageVector = Icons.Default.DeleteOutline,
                                        contentDescription = "Remove task",
                                        tint = if (tasks.size > 1) MaterialTheme.colorScheme.error.copy(alpha = 0.8f) else MaterialTheme.colorScheme.outline.copy(alpha = 0.3f),
                                        modifier = Modifier.size(18.dp)
                                    )
                                }
                            }
                        }
                    }

                    Spacer(modifier = Modifier.height(10.dp))

                    // Action buttons (Add custom task & Reset to default)
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        OutlinedButton(
                            onClick = {
                                val remaining = (100 - totalWeight).coerceAtLeast(0)
                                val newId = "task_${System.currentTimeMillis()}_${tasks.size}"
                                tasks = tasks + EditableTaskItem(
                                    id = newId,
                                    label = "",
                                    weightText = if (remaining > 0) remaining.toString() else "10"
                                )
                            },
                            shape = RoundedCornerShape(10.dp),
                            border = BorderStroke(1.dp, PremiumGold),
                            colors = ButtonDefaults.outlinedButtonColors(contentColor = PremiumGold)
                        ) {
                            Icon(imageVector = Icons.Default.Add, contentDescription = null, modifier = Modifier.size(16.dp))
                            Spacer(modifier = Modifier.width(4.dp))
                            Text("+ নতুন টাস্ক", fontSize = 12.sp, fontWeight = FontWeight.Bold)
                        }

                        TextButton(
                            onClick = {
                                tasks = defaultTasks.map {
                                    EditableTaskItem(
                                        id = it.id,
                                        label = it.label,
                                        weightText = it.weight.toString()
                                    )
                                }
                            }
                        ) {
                            Icon(imageVector = Icons.Default.RestartAlt, contentDescription = null, modifier = Modifier.size(14.dp))
                            Spacer(modifier = Modifier.width(4.dp))
                            Text("ডিফল্ট টাস্ক সেট", fontSize = 11.sp, color = MaterialTheme.colorScheme.onSurfaceVariant)
                        }
                    }
                }

                Spacer(modifier = Modifier.height(12.dp))
                HorizontalDivider(color = MaterialTheme.colorScheme.outline.copy(alpha = 0.2f))
                Spacer(modifier = Modifier.height(10.dp))

                // Footer Buttons
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    TextButton(
                        onClick = onDismiss,
                        modifier = Modifier.weight(1f)
                    ) {
                        Text("বাতিল")
                    }

                    Button(
                        onClick = {
                            if (canSave) {
                                val finalizedTasks = tasks.map {
                                    StudyTask(
                                        id = it.id,
                                        label = it.label.trim(),
                                        weight = it.weight
                                    )
                                }
                                onSave(title.trim(), driveLink.trim(), finalizedTasks)
                            }
                        },
                        enabled = canSave,
                        modifier = Modifier.weight(2f),
                        shape = RoundedCornerShape(12.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = PremiumGold,
                            contentColor = Color(0xFF121212),
                            disabledContainerColor = MaterialTheme.colorScheme.surfaceVariant,
                            disabledContentColor = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.6f)
                        )
                    ) {
                        Text(
                            text = if (isHundred) "সংরক্ষণ করুন (Save)" else "মোট ১০০% করুন ($totalWeight%)",
                            fontWeight = FontWeight.Bold,
                            fontSize = 13.sp
                        )
                    }
                }
            }
        }
    }
}
