package com.example.crud_sqlite_giuaky

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_add_user.*

class AddUserActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        val database = FirebaseDatabase.getInstance()
        val myRef = database.getReference("SanPham")
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_user)
        print("Dang ky")
        save_button.setOnClickListener{
            print("Dang ki")
            val name = student_name.text.toString()
            val type = student_email.text.toString()
            val cost = student_password.text.toString()
            val imgUrl = student_repassword.text.toString().trim()
            val key = myRef.push().key
            if (key!= null){
                val sanPham = ProductModel(name, type, cost, imgUrl, key)
                myRef.child(key).setValue(sanPham).addOnCompleteListener{
                    Toast.makeText(this, "Product added successfully",Toast.LENGTH_SHORT).show()
                    val intent = Intent(this, MainActivity::class.java)
                    startActivity(intent)
                    finish()
                }.addOnFailureListener{
                    Toast.makeText(this, "ERROR: ${it.message}",Toast.LENGTH_SHORT).show()
                    finish()
                }
            }
            Toast.makeText(this, "ERROR: Cannot create key",Toast.LENGTH_SHORT).show()
            finish()

        }
    }
}