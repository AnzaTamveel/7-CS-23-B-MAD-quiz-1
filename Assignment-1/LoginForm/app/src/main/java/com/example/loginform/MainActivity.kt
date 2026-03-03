package com.example.loginform

import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.RadioButton
import android.widget.Spinner
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputEditText

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

        // Wire UI
        val spinner = findViewById<Spinner>(R.id.spinnerRole)
        val roles = listOf("User", "Admin", "Moderator", "Guest")
        spinner.adapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, roles)

        val etEmail = findViewById<TextInputEditText>(R.id.etEmail)
        val etPassword = findViewById<TextInputEditText>(R.id.etPassword)
        val btnSubmit = findViewById<MaterialButton>(R.id.btnSubmit)

        btnSubmit.setOnClickListener {
            val email = etEmail.text?.toString()?.trim().orEmpty()
            val password = etPassword.text?.toString().orEmpty()

            val cbTerms = findViewById<View>(R.id.cbTerms)
            val isTermsChecked = (cbTerms as? android.widget.CheckBox)?.isChecked ?: false

            // Read selected radio
            val rg = findViewById<android.widget.RadioGroup>(R.id.rgAccountType)
            val selectedId = rg.checkedRadioButtonId
            val accountType = if (selectedId != -1) {
                val rb = findViewById<RadioButton>(selectedId)
                rb.text.toString()
            } else {
                "Not selected"
            }

            val role = spinner.selectedItem?.toString() ?: ""

            // Basic validation
            if (email.isEmpty()) {
                Toast.makeText(this, "Please enter your email", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            if (password.isEmpty()) {
                Toast.makeText(this, "Please enter your password", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            if (!isTermsChecked) {
                Toast.makeText(this, "Please accept the Terms & Conditions", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // Show summary
            val remember = findViewById<android.widget.CheckBox>(R.id.cbRemember).isChecked
            val msg = "Email: $email\nAccount: $accountType\nRole: $role\nRemember: $remember"
            Toast.makeText(this, msg, Toast.LENGTH_LONG).show()
        }
    }
}