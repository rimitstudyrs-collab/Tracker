package com.example.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user_license")
data class UserLicenseEntity(
    @PrimaryKey val email: String,
    val accessCode: String,
    val expiresAt: Long,
    val isActive: Boolean = true,
    val selectedTrack: String = "hsc",
    val lastActiveAt: Long = System.currentTimeMillis()
)
