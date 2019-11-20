package com.example.timehunter

import androidx.room.*

@Entity
data class Notification(
    @PrimaryKey val uid: Int,
    @ColumnInfo(name = "icon") val firstName: String?,
    @ColumnInfo(name = "last_name") val lastName: String?,
    @ColumnInfo(name = "ref") val ref: String?
)

@Dao
interface NotificationDao {
    @Query("SELECT * FROM notification LIMIT :limit")
    fun getSome(limit: String): List<Notification>

    @Insert
    fun insert(notification: Notification)

}

@Database(entities = arrayOf(Notification::class), version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun notificationDao(): NotificationDao
}