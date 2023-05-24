package com.example.markingactivity

import androidx.room.Dao
import androidx.room.Query

@Dao
interface SubjectDao {
    @Query("SELECT * FROM subjects")
    suspend fun getAllSubjects(): List<Subject>

    @Query("SELECT * FROM subjects WHERE id = :subjectId")
    suspend fun getSubjectById(subjectId: String): Subject?
}
