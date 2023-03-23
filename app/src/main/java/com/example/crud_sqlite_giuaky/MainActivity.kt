package com.example.crud_sqlite_giuaky

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private lateinit var dbHelper: StudentDatabaseHelper
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        add_button.setOnClickListener{
            startActivity(Intent(this, AddStudentActivity::class.java))
        }
        dbHelper = StudentDatabaseHelper(this)
        val students = dbHelper.getAllStudent()
        student_list_view.adapter = AdapterStudent(this, students)
        student_list_view.setOnClickListener{
            Toast.makeText(this, "Error: student not found", Toast.LENGTH_SHORT).show()
        }
    }
}