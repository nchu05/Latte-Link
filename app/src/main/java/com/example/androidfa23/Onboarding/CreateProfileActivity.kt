package com.example.androidfa23.Onboarding

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.activity.result.contract.ActivityResultContracts
import com.example.androidfa23.MainActivity
import com.example.androidfa23.R

class CreateProfileActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_profile)

        val continueButton : Button = findViewById(R.id.continueButton)
        continueButton.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            com.example.androidfa23.intentLauncher.launch(intent)
        }
        com.example.androidfa23.intentLauncher = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()
        ) {}
    }
}