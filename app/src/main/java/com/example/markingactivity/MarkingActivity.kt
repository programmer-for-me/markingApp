package com.example.markingactivity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_marking.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MarkingActivity : AppCompatActivity() {
    private lateinit var studentDao: StudentDao
    private lateinit var subjectDao: SubjectDao
    private lateinit var markDao: MarkDao

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_marking)

        studentDao = AppDatabase.getDatabase(this).studentDao()
        subjectDao = AppDatabase.getDatabase(this).subjectDao()
        markDao = AppDatabase.getDatabase(this).markDao()

        val subjectId = intent.getStringExtra("subjectId")
        val groupId = intent.getStringExtra("groupId")

        GlobalScope.launch {
            val students = withContext(Dispatchers.IO) {
                studentDao.getAllStudents()
            }

            val subject = withContext(Dispatchers.IO) {
                subjectDao.getSubjectById(subjectId)
            }

            withContext(Dispatchers.Main) {
                subjectNameTextView.text = subject?.name ?: ""

                val studentNames = students.filter { it.group == groupId }.map { it.name }
                val adapter = ArrayAdapter(this@MarkingActivity, android.R.layout.simple_list_item_1, studentNames)
                studentsListView.adapter = adapter

                studentsListView.setOnItemClickListener { _, _, position, _ ->
                    val selectedStudent = students.filter { it.group == groupId }[position]
                    val studentId = selectedStudent.id
                    markStudent(studentId, subjectId)
                }
            }
        }
    }

    private fun markStudent(studentId: String, subjectId: String) {
        // Implement marking logic here
    }
}
