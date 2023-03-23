package com.example.crud_sqlite_giuaky

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_add_student.*
import kotlinx.android.synthetic.main.activity_main.*

class AddStudentActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        val dbHelper = StudentDatabaseHelper(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_student)
        print("Dang ky")
        save_button.setOnClickListener{
            print("Dang ki")
            val name = student_name.text.toString()
            val email = student_email.text.toString()
            val password = student_password.text.toString()
            val repassword = student_repassword.text.toString()
            print(" $password $repassword")
            if (!password.equals(repassword) ){
                Toast.makeText(this, "Vui lòng nhập đúng yêu cầu",  Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }else{
                dbHelper.addStudent(name.toString(), email.toString(), password.toString())
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
            }
            finish()
        }
    }
}