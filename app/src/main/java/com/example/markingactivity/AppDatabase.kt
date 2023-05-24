package com.example.markingactivity

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [Student::class, Subject::class, Mark::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun studentDao(): StudentDao
    abstract fun subjectDao(): SubjectDao
    abstract fun markDao(): MarkDao
}
