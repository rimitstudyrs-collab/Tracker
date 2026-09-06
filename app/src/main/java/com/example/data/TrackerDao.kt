package com.example.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface TrackerDao {

    @Query("SELECT * FROM task_progress WHERE trackId = :trackId")
    fun getProgressForTrack(trackId: String): Flow<List<TaskProgressEntity>>

    @Query("SELECT * FROM task_progress")
    fun getAllProgress(): Flow<List<TaskProgressEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertOrUpdateTask(progress: TaskProgressEntity)

    @Query("DELETE FROM task_progress WHERE trackId = :trackId AND subjectName = :subjectName")
    suspend fun clearSubjectProgress(trackId: String, subjectName: String)

    @Query("DELETE FROM task_progress")
    suspend fun clearAllProgress()

    @Query("SELECT * FROM user_license LIMIT 1")
    fun getUserLicense(): Flow<UserLicenseEntity?>

    @Query("SELECT * FROM user_license WHERE email = :email LIMIT 1")
    fun getLicenseByEmailFlow(email: String): Flow<UserLicenseEntity?>

    @Query("SELECT * FROM user_license WHERE email = :email LIMIT 1")
    suspend fun getLicenseByEmail(email: String): UserLicenseEntity?

    @Query("SELECT * FROM user_license ORDER BY expiresAt DESC")
    fun getAllLicenses(): Flow<List<UserLicenseEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUserLicense(license: UserLicenseEntity)

    @Query("UPDATE user_license SET selectedTrack = :trackId WHERE email = :email")
    suspend fun updateSelectedTrack(email: String, trackId: String)

    @Query("UPDATE user_license SET isActive = :isActive WHERE email = :email")
    suspend fun updateUserStatus(email: String, isActive: Boolean)

    @Query("DELETE FROM user_license WHERE email = :email")
    suspend fun deleteLicense(email: String)

    @Query("DELETE FROM user_license")
    suspend fun clearUser()
}
