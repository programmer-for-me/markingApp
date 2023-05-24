package com.example.markingactivity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import kotlinx.android.synthetic.main.activity_score.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ScoreActivity : AppCompatActivity() {
    private lateinit var studentDao: StudentDao
    private lateinit var subjectDao: SubjectDao
    private lateinit var markDao: MarkDao

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_score)

        studentDao = AppDatabase.getDatabase(this).studentDao()
        subjectDao = AppDatabase.getDatabase(this).subjectDao()
        markDao = AppDatabase.getDatabase(this).markDao()

        val studentId = intent.getStringExtra("studentId")

        GlobalScope.launch {
            val student = withContext(Dispatchers.IO) {
                studentDao.getStudentById(studentId)
            }

            val marks = withContext(Dispatchers.IO) {
                markDao.getMarksForStudent(studentId)
            }

            withContext(Dispatchers.Main) {
                studentNameTextView.text = student?.name ?: ""

                val subjectNames = marks.mapNotNull { mark ->
                    val subject = subjectDao.getSubjectById(mark.subjectId)
                    subject?.name
                }
                val adapter = ArrayAdapter(this@ScoreActivity, android.R.layout.simple_list_item_1, subjectNames)
                scoresListView.adapter = adapter

                scoresListView.setOnItemClickListener { _, _, position, _ ->
                    val selectedMark = marks[position]
                    Toast.makeText(this@ScoreActivity, "Score: ${selectedMark.score}", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}
