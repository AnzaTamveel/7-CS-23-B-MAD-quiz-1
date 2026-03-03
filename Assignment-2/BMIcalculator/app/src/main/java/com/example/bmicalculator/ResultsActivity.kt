package com.example.bmicalculator

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import java.util.Locale

class ResultsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_results)

        val nameTv = findViewById<TextView>(R.id.nameTv)
        val ageTv = findViewById<TextView>(R.id.ageTv)
        val heightMeterTv = findViewById<TextView>(R.id.heightMeterTv)
        val bmiTv = findViewById<TextView>(R.id.bmiTv)
        val categoryTv = findViewById<TextView>(R.id.categoryTv)

        val name = intent.getStringExtra("name") ?: ""
        val age = intent.getIntExtra("age", -1)
        val weight = intent.getDoubleExtra("weight", -1.0)
        val heightCm = intent.getDoubleExtra("height_cm", -1.0)

        nameTv.text = name
        ageTv.text = if (age >= 0) "Age: $age" else ""

        if (heightCm > 0.0) {
            val heightM = heightCm / 100.0
            heightMeterTv.text = String.format(Locale.US, "Height(m) = %.2f", heightM)
            if (weight > 0.0) {
                val bmi = weight / (heightM * heightM)
                val bmiRounded = String.format(Locale.US, "%.2f", bmi)
                bmiTv.text = String.format(Locale.US, "BMI: %s", bmiRounded)

                val (category, colorRes) = when {
                    bmi < 18.5 -> Pair("Underweight", R.color.bmi_underweight)
                    bmi < 25.0 -> Pair("Normal", R.color.bmi_normal)
                    else -> Pair("Overweight", R.color.bmi_overweight)
                }

                val color = ContextCompat.getColor(this, colorRes)
                categoryTv.text = category
                categoryTv.setTextColor(color)
            }
        }
    }
}
