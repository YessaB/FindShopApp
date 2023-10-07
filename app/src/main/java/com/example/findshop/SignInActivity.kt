package com.example.findshop

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class SignInActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in)
        supportActionBar?.hide()

        initViews()
    }

    @SuppressLint("Range")
    private fun initViews() {
        val button = findViewById<Button>(R.id.sign_in_btn)
        val text = findViewById<TextView>(R.id.text_sign_up)
        val email = findViewById<EditText>(R.id.email)
        val password = findViewById<EditText>(R.id.password)
        val helper = DBHelper(applicationContext)
        val db = helper.readableDatabase

        button.setOnClickListener {
            if (email.text.length != 0 && password.text.length != 0) {
                val args = listOf<String>(email.text.toString(), password.text.toString()).toTypedArray()
                val rs = db.rawQuery("SELECT * FROM USERS WHERE UEMAIL = ? AND PWD = ?", args)
                if (rs.moveToNext()) {
                    val id = rs.getInt(rs.getColumnIndex("USERID"))
                    val name = rs.getString(rs.getColumnIndex("FNAME"))
                    val emaily=rs.getString(rs.getColumnIndex("UEMAIL"))

                    Toast.makeText(applicationContext, "Succesfully logged In !!", Toast.LENGTH_SHORT).show()
                    val intent = Intent(this, HomeActivity::class.java)
                    intent.putExtra("puserID", id)
                    intent.putExtra("pusername", name)
                    intent.putExtra("puseremail", emaily)
                    startActivity(intent)
                } else {
                    Toast.makeText(applicationContext, "Invalid Credentials, Try Again !", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(applicationContext, "Fill all inputs", Toast.LENGTH_SHORT).show()
            }
        }
        text.setOnClickListener {
            val intent = Intent(this, SignUpActivity::class.java)
            startActivity(intent)
        }
    }
}