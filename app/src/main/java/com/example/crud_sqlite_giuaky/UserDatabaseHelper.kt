package com.example.crud_sqlite_giuaky

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class UserDatabaseHelper(context: Context): SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION){

    companion object{
        private const val DATABASE_VERSION = 1
        private const val DATABASE_NAME = "STUDENT_MANAGER_2"
        private const val TABLE_NAME = "student"
        private const val C_ID = "id"
        private const val C_NAME = "name"
        private const val C_EMAIL = "email"
        private const val C_PASS = "pass"
    }

    override fun onCreate(db: SQLiteDatabase?) {
        val CREATE_TABLE = "CREATE TABLE $TABLE_NAME ($C_ID INTEGER PRIMARY KEY AUTOINCREMENT, $C_NAME TEXT, $C_EMAIL TEXT, $C_PASS TEXT)"
        db?.execSQL(CREATE_TABLE)
    }

    override fun onUpgrade(db: SQLiteDatabase?, p1: Int, p2: Int) {
        db?.execSQL("DROP TABLE IF EXISTS $TABLE_NAME ")
        onCreate(db)
    }


    @SuppressLint("Range")
    fun getAllStudent(): ArrayList<UserModal>{
        var studentList = ArrayList<UserModal>()
        val db = writableDatabase
        val cursor = db.rawQuery("SELECT * FROM $TABLE_NAME", null)
        if (cursor.moveToFirst()){
            do{
                val userModal = UserModal()
                userModal.id = cursor.getInt(cursor.getColumnIndex(C_ID))
                userModal.name = cursor.getString(cursor.getColumnIndex(C_NAME))
                userModal.email = cursor.getString(cursor.getColumnIndex(C_EMAIL))
                studentList.add(userModal)
            }while (cursor.moveToNext())
        }
        db.close()
        cursor.close()
        return studentList
    }

    @SuppressLint("Range")
    fun getStudentByID(id: Int): UserModal{
        val userModal = UserModal()
        val db = writableDatabase
        val cursor = db.rawQuery("SELECT * FROM $TABLE_NAME WHERE $C_ID = $id", null)
        cursor?.moveToFirst()
        userModal.id = id
        userModal.name = cursor.getString(cursor.getColumnIndex(C_NAME))
        userModal.email = cursor.getString(cursor.getColumnIndex(C_EMAIL))
        cursor.close()
        db.close()
        return userModal

    }

    fun addStudent(name: String?, email: String?, password: String?){
        val db = this.writableDatabase
        val values = ContentValues()

        values.put(C_NAME, name)
        values.put(C_EMAIL, email)
        values.put(C_PASS, password)

        db.insert(TABLE_NAME, null, values)
        db.close()
    }

    fun deleteStudent(id: Int){
        val db = this.writableDatabase
        db.execSQL("DELETE FROM $TABLE_NAME WHERE $C_ID = $id")
        db.close()
    }

    fun updateStudent(userModal: UserModal){
        val db = this.writableDatabase
        val values = ContentValues()
        values.put(C_ID, userModal.id)
        values.put(C_NAME, userModal.name)
        values.put(C_EMAIL, userModal.email)
        values.put(C_PASS, userModal.password)
        db.update(TABLE_NAME, values, C_ID + "=?", arrayOf(userModal.id.toString()))
        db.close()

    }

}