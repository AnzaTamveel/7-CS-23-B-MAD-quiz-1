package com.example.assignment_3

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.appcompat.app.AppCompatActivity

class RegisterActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        val spinner = findViewById<Spinner>(R.id.spCountry)

        val countries = arrayOf("Pakistan", "India", "USA", "UK")

        val adapter = ArrayAdapter(this,
            android.R.layout.simple_spinner_item, countries)

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner.adapter = adapter
    }
}