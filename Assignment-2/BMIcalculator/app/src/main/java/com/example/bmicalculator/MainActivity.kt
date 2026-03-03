package com.example.bmicalculator

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val nameInput = findViewById<EditText>(R.id.nameInput)
        val ageInput = findViewById<EditText>(R.id.ageInput)
        val weightInput = findViewById<EditText>(R.id.weightInput)
        val heightInput = findViewById<EditText>(R.id.heightInput)
        val showButton = findViewById<Button>(R.id.showButton)

        showButton.setOnClickListener {
            val name = nameInput.text.toString().trim()
            val ageStr = ageInput.text.toString().trim()
            val weightStr = weightInput.text.toString().trim()
            val heightStr = heightInput.text.toString().trim()

            var hasError = false
            if (name.isEmpty()) {
                nameInput.error = "Required"
                hasError = true
            }
            if (ageStr.isEmpty()) {
                ageInput.error = "Required"
                hasError = true
            }
            if (weightStr.isEmpty()) {
                weightInput.error = "Required"
                hasError = true
            }
            if (heightStr.isEmpty()) {
                heightInput.error = "Required"
                hasError = true
            }
            if (hasError) return@setOnClickListener

            val age = ageStr.toIntOrNull()
            val weight = weightStr.toDoubleOrNull()
            val height = heightStr.toDoubleOrNull()

            if (age == null || age <= 0) {
                ageInput.error = "Enter valid age"
                return@setOnClickListener
            }
            if (weight == null || weight <= 0.0) {
                weightInput.error = "Enter valid weight"
                return@setOnClickListener
            }
            if (height == null || height <= 0.0) {
                heightInput.error = "Enter valid height"
                return@setOnClickListener
            }

            val intent = Intent(this, ResultsActivity::class.java).apply {
                putExtra("name", name)
                putExtra("age", age)
                putExtra("weight", weight)
                putExtra("height_cm", height)
            }
            startActivity(intent)
        }
    }
}