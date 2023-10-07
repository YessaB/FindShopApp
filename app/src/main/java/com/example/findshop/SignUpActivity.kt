package com.example.findshop

import android.content.ContentValues
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast

class SignUpActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)
        supportActionBar?.hide()

        initViews()
    }

    private fun initViews() {
        val button = findViewById<Button>(R.id.sign_up_btn)
        val text = findViewById<TextView>(R.id.text_sign_in)
        val email = findViewById<EditText>(R.id.email)
        val name = findViewById<EditText>(R.id.name)
        val password = findViewById<EditText>(R.id.password)
        val confirm_password = findViewById<EditText>(R.id.confirm_password)

        button.setOnClickListener {
            var helper =DBHelper(applicationContext)
            var db =helper.readableDatabase

            if (email.text.isEmpty()
                || password.text.isEmpty()
                || name.text.isEmpty()
                || confirm_password.text.isEmpty()) {
                Toast.makeText(applicationContext, "Fill all inputs!!", Toast.LENGTH_SHORT).show()
            } else if (password.text.toString() != confirm_password.text.toString()) {
                Toast.makeText(applicationContext, "Passwords do not match!", Toast.LENGTH_SHORT).show()
            } else {
                var cv =ContentValues()
                cv.put("FNAME",name.text.toString())
                cv.put("UEMAIL",email.text.toString())
                cv.put("PWD",password.text.toString())
                db.insert("USERS",null,cv)
                name.setText("")
                email.setText("")
                password.setText("")
                confirm_password.setText("")
                val intent = Intent(this, SignInActivity::class.java)
                Toast.makeText(applicationContext, "Your account was created succesfully!, you may login now!", Toast.LENGTH_SHORT).show()
                startActivity(intent)
                finish()
            }
        }
        text.setOnClickListener {
            val intent = Intent(this, SignInActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}