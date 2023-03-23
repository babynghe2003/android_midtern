package com.example.crud_sqlite_giuaky

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_update_user.*

class UpdateUserActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        val dbHelper = UserDatabaseHelper(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_update_user)
        val studentID = intent.getIntExtra("student_id", -1)
        val student = dbHelper.getStudentByID(studentID.toInt())
        new_student_name.setText(student.name)
        new_student_email.setText(student.email)
        new_student_password.setText("")
        update_button.setOnClickListener{
            student.name = new_student_name.text.toString()
            student.email = new_student_email.text.toString()
            student.password = new_student_password.text.toString()
            dbHelper.updateStudent(student)
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
        delete_button.setOnClickListener{
            dbHelper.deleteStudent(studentID.toInt())
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

}