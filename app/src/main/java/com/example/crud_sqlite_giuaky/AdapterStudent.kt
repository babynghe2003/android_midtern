package com.example.crud_sqlite_giuaky

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView

class AdapterStudent(val context: Context, val recordList: ArrayList<Student>): RecyclerView.Adapter<AdapterStudent.HolderRecord>() {
    class HolderRecord(itemView: View): RecyclerView.ViewHolder(itemView){
        var nameTy: TextView = itemView.findViewById(R.id.name_textview)
        var emailTy: TextView = itemView.findViewById(R.id.email_textview)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HolderRecord {
        return HolderRecord(LayoutInflater.from(context).inflate(R.layout.student_list, parent, false))
    }

    override fun getItemCount(): Int {
        return recordList!!.size
    }

    override fun onBindViewHolder(holder: HolderRecord, position: Int) {
        val student = recordList!!.get(position)
        val id = student.id
        val name = student.name
        val email = student.email
        holder.nameTy.text = name
        holder.emailTy.text = email

        holder.itemView.setOnClickListener{
            val intent = Intent(context, UpdateStudentActivity::class.java)
            intent.putExtra("student_id", student.id)
            context.startActivity(intent)
        }
    }
}