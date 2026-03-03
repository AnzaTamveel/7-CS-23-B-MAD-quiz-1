package com.example.assignment_3

import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class QuizActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz)

        val question = findViewById<TextView>(R.id.tvQuestion)
        val radioGroup = findViewById<RadioGroup>(R.id.rgOptions)
        val submitButton = findViewById<Button>(R.id.btnSubmit)
        val subscribeButton = findViewById<Button>(R.id.btnSubscribe)

        submitButton.setOnClickListener {
            val selectedId = radioGroup.checkedRadioButtonId
            if (selectedId != -1) {
                val selectedOption = findViewById<RadioButton>(selectedId)
                val answer = selectedOption.text.toString()
                if (answer == "9") {
                    Toast.makeText(this, "Correct!", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(this, "Wrong! Try again.", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(this, "Please select an option", Toast.LENGTH_SHORT).show()
            }
        }

        subscribeButton.setOnClickListener {
            Toast.makeText(this, "Subscribed! 🎉", Toast.LENGTH_SHORT).show()
        }
    }
}