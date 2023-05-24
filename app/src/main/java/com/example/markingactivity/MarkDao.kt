package com.example.markingactivity

import androidx.room.Dao
import androidx.room.Query

@Dao
interface MarkDao {
    @Query("SELECT * FROM marks WHERE studentId = :studentId AND subjectId = :subjectId")
    suspend fun getMark(studentId: String, subjectId: String): Mark?

    @Query("SELECT * FROM marks WHERE studentId = :studentId")
    suspend fun getMarksForStudent(studentId: String): List<Mark>

    @Query("SELECT * FROM marks WHERE subjectId = :subjectId")
    suspend fun getMarksForSubject(subjectId: String): List<Mark>

    @Query("INSERT INTO marks (studentId, subjectId, score) VALUES (:studentId, :subjectId, :score)")
    suspend fun addMark(studentId: String, subjectId: String, score: Double)

    @Query("UPDATE marks SET score = :score WHERE id = :markId")
    suspend fun updateMark(markId: Long, score: Double)

    @Query("DELETE FROM marks WHERE id = :markId")
    suspend fun deleteMark(markId: Long)
}
