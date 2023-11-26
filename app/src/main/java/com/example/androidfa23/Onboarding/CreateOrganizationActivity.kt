package com.example.androidfa23.Onboarding

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import com.example.androidfa23.MainActivity
import com.example.androidfa23.R

class CreateOrganizationActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_organization)

        val continueButton : Button = findViewById(R.id.continueButton)
        continueButton.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        val backButton : ImageView = findViewById(R.id.backButton)
        backButton.setOnClickListener {
            val intent = Intent(this, SpecifyRoleActivity::class.java)
            startActivity(intent)
        }

        val addAffiliationButton : Button = findViewById(R.id.addAffiliationButton)
        addAffiliationButton.setOnClickListener {
            val newFragment = OrgAffliationsDialogFragment()
            newFragment.show(supportFragmentManager, "Affiliation")
        }
    }
}