package com.example.data

import androidx.room.Entity

@Entity(
    tableName = "task_progress",
    primaryKeys = ["trackId", "subjectName", "chapterIndex", "taskId"]
)
data class TaskProgressEntity(
    val trackId: String,
    val subjectName: String,
    val chapterIndex: Int,
    val taskId: String,
    val isCompleted: Boolean,
    val updatedAt: Long = System.currentTimeMillis()
)
