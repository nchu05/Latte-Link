package com.example.androidfa23.Onboarding

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import com.example.androidfa23.LoginActivity
import com.example.androidfa23.R

class SpecifyRoleActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_specify_role)

        val studentButton : Button = findViewById(R.id.yourselfButton)
        val organizationButton : Button = findViewById(R.id.organizationButton)
        val backButton : ImageView = findViewById(R.id.backButton)
        backButton.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }
        studentButton.setOnClickListener{
            val intent = Intent(this, CreateProfileActivity::class.java)
            startActivity(intent)
        }

        organizationButton.setOnClickListener {
            val intent = Intent(this, CreateOrganizationActivity::class.java)
            startActivity(intent)
        }
    }
}