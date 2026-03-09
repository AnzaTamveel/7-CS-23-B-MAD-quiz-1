package com.example.a2023_cs_87appointmentbookingapplication

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class ConfirmationActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_confirmation)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.confirmation_container)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        displayAppointmentDetails()
        setupBackButton()
    }

    private fun displayAppointmentDetails() {
        val tvName = findViewById<TextView>(R.id.tv_confirm_name)
        val tvPhone = findViewById<TextView>(R.id.tv_confirm_phone)
        val tvEmail = findViewById<TextView>(R.id.tv_confirm_email)
        val tvType = findViewById<TextView>(R.id.tv_confirm_type)
        val tvDate = findViewById<TextView>(R.id.tv_confirm_date)
        val tvTime = findViewById<TextView>(R.id.tv_confirm_time)
        val tvGender = findViewById<TextView>(R.id.tv_confirm_gender)

        val bundle = intent.extras
        if (bundle != null) {
            tvName.text = "Name: ${bundle.getString("name", "N/A")}"
            tvPhone.text = "Phone: ${bundle.getString("phone", "N/A")}"
            tvEmail.text = "Email: ${bundle.getString("email", "N/A")}"
            tvType.text = "Appointment Type: ${bundle.getString("type", "N/A")}"
            tvDate.text = "Date: ${bundle.getString("date", "N/A")}"
            tvTime.text = "Time: ${bundle.getString("time", "N/A")}"
            tvGender.text = "Gender: ${bundle.getString("gender", "N/A")}"
        }
    }

    private fun setupBackButton() {
        val btnBack = findViewById<Button>(R.id.btn_back_home)
        btnBack.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }
    }
}

