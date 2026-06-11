package com.example.a2023_cs_87appointmentbookingapplication

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class BookAppointmentActivity : AppCompatActivity() {
    private lateinit var etName: EditText
    private lateinit var etPhone: EditText
    private lateinit var etEmail: EditText
    private lateinit var spinnerType: Spinner
    private lateinit var tvDate: TextView
    private lateinit var tvTime: TextView
    private lateinit var radioGroup: RadioGroup
    private lateinit var cbTerms: CheckBox
    private lateinit var btnConfirm: Button

    private var selectedDate = ""
    private var selectedTime = ""
    private val calendar = Calendar.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_book_appointment)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.book_container)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        initializeViews()
        setupDatePicker()
        setupTimePicker()
        setupConfirmButton()
    }

    private fun initializeViews() {
        etName = findViewById(R.id.et_name)
        etPhone = findViewById(R.id.et_phone)
        etEmail = findViewById(R.id.et_email)
        spinnerType = findViewById(R.id.spinner_type)
        tvDate = findViewById(R.id.tv_date)
        tvTime = findViewById(R.id.tv_time)
        radioGroup = findViewById(R.id.radio_group_gender)
        cbTerms = findViewById(R.id.cb_terms)
        btnConfirm = findViewById(R.id.btn_confirm)
    }

    private fun setupDatePicker() {
        tvDate.setOnClickListener {
            val year = calendar.get(Calendar.YEAR)
            val month = calendar.get(Calendar.MONTH)
            val day = calendar.get(Calendar.DAY_OF_MONTH)

            DatePickerDialog(this, { _, selectedYear, selectedMonth, selectedDay ->
                calendar.set(selectedYear, selectedMonth, selectedDay)
                val dateFormat = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
                selectedDate = dateFormat.format(calendar.time)
                tvDate.text = selectedDate
            }, year, month, day).show()
        }
    }

    private fun setupTimePicker() {
        tvTime.setOnClickListener {
            val hour = calendar.get(Calendar.HOUR_OF_DAY)
            val minute = calendar.get(Calendar.MINUTE)

            TimePickerDialog(this, { _, selectedHour, selectedMinute ->
                selectedTime = String.format(Locale.getDefault(), "%02d:%02d", selectedHour, selectedMinute)
                tvTime.text = selectedTime
            }, hour, minute, true).show()
        }
    }

    private fun setupConfirmButton() {
        btnConfirm.setOnClickListener {
            if (validateForm()) {
                proceedToConfirmation()
            }
        }
    }

    private fun validateForm(): Boolean {
        // Name validation
        if (etName.text.toString().trim().isEmpty()) {
            Toast.makeText(this, R.string.validation_name_empty, Toast.LENGTH_SHORT).show()
            return false
        }

        // Phone validation
        val phone = etPhone.text.toString().trim()
        if (phone.isEmpty()) {
            Toast.makeText(this, R.string.validation_phone_empty, Toast.LENGTH_SHORT).show()
            return false
        }
        if (!isValidPhone(phone)) {
            Toast.makeText(this, R.string.validation_phone_invalid, Toast.LENGTH_SHORT).show()
            return false
        }

        // Email validation
        val email = etEmail.text.toString().trim()
        if (email.isEmpty()) {
            Toast.makeText(this, R.string.validation_email_empty, Toast.LENGTH_SHORT).show()
            return false
        }
        if (!isValidEmail(email)) {
            Toast.makeText(this, R.string.validation_email_invalid, Toast.LENGTH_SHORT).show()
            return false
        }

        // Appointment type validation
        if (spinnerType.selectedItemPosition == 0) {
            Toast.makeText(this, R.string.validation_type_empty, Toast.LENGTH_SHORT).show()
            return false
        }

        // Terms validation
        if (!cbTerms.isChecked) {
            Toast.makeText(this, R.string.validation_terms, Toast.LENGTH_SHORT).show()
            return false
        }

        return true
    }

    private fun isValidEmail(email: String): Boolean {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }

    private fun isValidPhone(phone: String): Boolean {
        return phone.length >= 10 && phone.all { it.isDigit() || it == '+' || it == '-' || it == ' ' }
    }

    private fun proceedToConfirmation() {
        val selectedRadioId = radioGroup.checkedRadioButtonId
        val gender = if (selectedRadioId != -1) {
            findViewById<RadioButton>(selectedRadioId).text.toString()
        } else {
            ""
        }

        val intent = Intent(this, ConfirmationActivity::class.java).apply {
            putExtra("name", etName.text.toString().trim())
            putExtra("phone", etPhone.text.toString().trim())
            putExtra("email", etEmail.text.toString().trim())
            putExtra("type", spinnerType.selectedItem.toString())
            putExtra("date", selectedDate)
            putExtra("time", selectedTime)
            putExtra("gender", gender)
        }
        startActivity(intent)
        finish()
    }
}

