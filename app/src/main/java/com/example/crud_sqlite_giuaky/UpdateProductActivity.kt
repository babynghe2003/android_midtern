package com.example.crud_sqlite_giuaky

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_update_user.*

class UpdateProductActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val database = FirebaseDatabase.getInstance()
        val myRef = database.getReference("SanPham")
        setContentView(R.layout.activity_update_user)
        val productID = intent.getStringExtra("student_id")?:""
        var sanPhamRef = myRef.child(productID)
        sanPhamRef.addListenerForSingleValueEvent(object: ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                val sanPham = snapshot.getValue(ProductModel::class.java)
                if (sanPham != null){
                    new_product_name.setText(sanPham.nameProduct)
                    new_product_email.setText(sanPham.typeProduct)
                    new_product_cost.setText(sanPham.costProduct)
                    new_student_imgURL.setText(sanPham.imageUrlProduct)
                }
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }
        })
        update_button.setOnClickListener{
            val name = new_product_name.text.toString()
            val type = new_product_email.text.toString()
            val cost = new_product_cost.text.toString()
            val imgURL = new_student_imgURL.text.toString()
            var product: ProductModel = ProductModel(name, type, cost, imgURL, productID)
            myRef.child(productID).setValue(product)
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
        delete_button.setOnClickListener{
            myRef.child(productID).removeValue()
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

}