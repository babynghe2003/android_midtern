package com.example.crud_sqlite_giuaky

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class ProductAdapter(val context: Context, val recordList: ArrayList<ProductModel>): RecyclerView.Adapter<ProductAdapter.HolderRecord>() {
    class HolderRecord(itemView: View): RecyclerView.ViewHolder(itemView){
        var nameTy: TextView = itemView.findViewById(R.id.name_textview)
        var typeTy: TextView = itemView.findViewById(R.id.type_textview)
        var costTy: TextView = itemView.findViewById(R.id.cost_textview)
        var imagePr: ImageView = itemView.findViewById(R.id.rPersonImage)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HolderRecord {
        return HolderRecord(LayoutInflater.from(context).inflate(R.layout.user_list, parent, false))
    }

    override fun getItemCount(): Int {
        return recordList!!.size
    }

    override fun onBindViewHolder(holder: HolderRecord, position: Int) {
        val sanPham = recordList!!.get(position)
        val name = sanPham.nameProduct
        val type = sanPham.typeProduct
        val cost = sanPham.costProduct + " đ"
        val imgURL = sanPham.imageUrlProduct
        holder.nameTy.text = name
        holder.typeTy.text = type
        holder.costTy.text = cost
        Glide.with(context)
            .load(imgURL)
            .error(R.drawable.baseline_shopping_cart_24)
            .into(holder.imagePr)

        holder.itemView.setOnClickListener{
            val intent = Intent(context, UpdateProductActivity::class.java)
            intent.putExtra("student_id", sanPham.id)
            context.startActivity(intent)
        }
    }
}