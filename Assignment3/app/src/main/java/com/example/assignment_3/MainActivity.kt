package com.example.assignment_3

import android.content.Intent
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val etInput = findViewById<EditText>(R.id.etInput)
        val btnSubmit = findViewById<Button>(R.id.btnSubmit)
        val tvResult = findViewById<TextView>(R.id.tvResult)
        val btnLogin = findViewById<Button>(R.id.btnLogin)

        btnSubmit.setOnClickListener {
            val input = etInput.text.toString()

            if (input.toIntOrNull() != null) {
                val number = input.toInt()
                tvResult.text = "Result: ${number * 3}"
            } else {
                tvResult.text = "Result: $input"
            }
        }

        btnLogin.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
        }
    }
}