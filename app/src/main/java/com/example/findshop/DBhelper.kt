package com.example.findshop

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DBHelper(context: Context) : SQLiteOpenHelper(context, "SHOPDETAILS", null, 1) {

    private val mContext: Context = context

    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL("CREATE TABLE USERS(USERID INTEGER PRIMARY KEY AUTOINCREMENT, FNAME TEXT, UEMAIL TEXT, PWD TEXT)")
        db?.execSQL("INSERT INTO USERS(FNAME, UEMAIL, PWD) VALUES('testuser', 'test@gmail.com', 'test123')")

        db?.execSQL("CREATE TABLE SHOPINFO(SHOPID INTEGER PRIMARY KEY AUTOINCREMENT, SHOPNAME TEXT, SHOPDESC TEXT, SHOPLOC TEXT, SHOPCONTACT TEXT, SHOPPHOTO TEXT, RATING REAL)")

        val values = ContentValues()
        values.put("SHOPNAME", "rideau center")
        values.put("SHOPDESC", "just a small store")
        values.put("SHOPLOC", "50 Rideau St, Ottawa, ON K1N 9J7")
        values.put("SHOPCONTACT", "1234")
        values.put("SHOPPHOTO", "https://upload.wikimedia.org/wikipedia/commons/0/05/Centre_Rideau_-_12.jpg")
        values.put("RATING", 3)

        db?.insert("SHOPINFO", null, values)
    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
        // handle database upgrades here
    }
}