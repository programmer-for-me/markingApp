package com.example.markingactivity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "marks")
data class Mark(
    @PrimaryKey(autoGenerate = true) val id: Long,
    val studentId: String,
    val subjectId: String,
    val score: Double
)

