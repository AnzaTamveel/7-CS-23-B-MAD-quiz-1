package com.example.testingkotlin

import android.os.Bundle
import android.view.View
import android.widget.*
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

        // Layout Containers
        val registrationLayout = findViewById<LinearLayout>(R.id.registration_layout)
        val toolsLayout = findViewById<LinearLayout>(R.id.tools_layout)

        // Registration UI elements
        val registerButton = findViewById<Button>(R.id.register_button)

        registerButton.setOnClickListener {
            // When Register is clicked, hide registration and show tools
            registrationLayout.visibility = View.GONE
            toolsLayout.visibility = View.VISIBLE
        }

        // Multiple of 3 Logic
        val numberInput = findViewById<EditText>(R.id.number_input)
        val checkButton = findViewById<Button>(R.id.check_button)
        val resultText = findViewById<TextView>(R.id.result_text)

        checkButton.setOnClickListener {
            val numberStr = numberInput.text.toString()
            if (numberStr.isNotEmpty()) {
                val number = numberStr.toInt()
                if (number % 3 == 0) {
                    resultText.text = "$number is a multiple of 3"
                } else {
                    resultText.text = "$number is NOT a multiple of 3"
                }
            } else {
                resultText.text = "Please enter a number"
            }
        }

        // Price Discount Logic
        val priceInput = findViewById<EditText>(R.id.price_input)
        val discountButton = findViewById<Button>(R.id.discount_button)
        val originalPriceText = findViewById<TextView>(R.id.original_price_text)
        val discountedPriceText = findViewById<TextView>(R.id.discounted_price_text)

        discountButton.setOnClickListener {
            val priceStr = priceInput.text.toString()
            if (priceStr.isNotEmpty()) {
                val originalPrice = priceStr.toDouble()
                val discountedPrice = originalPrice * 0.8 // 20% discount
                originalPriceText.text = "Original Price: $originalPrice"
                discountedPriceText.text = "Price after discount: $discountedPrice"
            } else {
                originalPriceText.text = "Please enter a price"
            }
        }
    }
}