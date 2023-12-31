package com.example.androidfa23.Onboarding

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import com.example.androidfa23.LoginActivity
import com.example.androidfa23.R

class SpecifyRoleActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_specify_role)

        val email = intent.extras?.getString("username")
        val password = intent.extras?.getString("password")

        if (email != null) {
            Log.e("JSON", email)
        }

        val studentButton : ImageView = findViewById(R.id.yourselfButton)
        val organizationButton : ImageView = findViewById(R.id.organizationButton)
        val backButton : ImageView = findViewById(R.id.backButton)
        val alreadyHaveAccount : Button = findViewById(R.id.alreadyHaveAccountButton)
        alreadyHaveAccount.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }
        backButton.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }
        studentButton.setOnClickListener{
            val intent = Intent(this, CreateProfileActivity::class.java)
            intent.putExtra("username", email)
            intent.putExtra("password", password)
            intent.putExtra("new", true)
            startActivity(intent)
        }
        organizationButton.setOnClickListener {
            val intent = Intent(this, CreateOrganizationActivity::class.java)
            intent.putExtra("username", email)
            intent.putExtra("password", password)
            intent.putExtra("new", true)
            startActivity(intent)
        }

    }
}