package com.example.crud_sqlite_giuaky

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val database = FirebaseDatabase.getInstance()
        val myRef = database.getReference("SanPham")
        setContentView(R.layout.activity_main)
        add_button.setOnClickListener{
            startActivity(Intent(this, AddUserActivity::class.java))
        }
        myRef.addValueEventListener(object: ValueEventListener{
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                val sanPhamList = ArrayList<ProductModel>()
                for (snapshot in dataSnapshot.children){
                    val sanPham = snapshot.getValue(ProductModel::class.java)
                    val sanPhamKey = snapshot.key
                    sanPham?.let{
                        it.id = sanPhamKey.toString()
                        sanPhamList.add(sanPham)

                    }
                }
                student_list_view.adapter = ProductAdapter(this@MainActivity, sanPhamList)
            }

            override fun onCancelled(error: DatabaseError) {
                Log.d("DB","Cannot connect to server")
            }

        })
        student_list_view.setOnClickListener{
            Toast.makeText(this, "Error: student not found", Toast.LENGTH_SHORT).show()
        }
    }
}