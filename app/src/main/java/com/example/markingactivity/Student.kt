package com.example.markingactivity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "students")
data class Student(
    @PrimaryKey val id: String,
    val name: String,
    val group: String
)

