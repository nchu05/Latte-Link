package com.example.androidfa23.Onboarding

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import androidx.activity.result.contract.ActivityResultContracts
import com.example.androidfa23.Browse.BrowseOrganizationFragment
import com.example.androidfa23.MainActivity
import com.example.androidfa23.R

class CreateProfileActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_profile)

        val backButton : ImageView = findViewById(R.id.backButton)
        backButton.setOnClickListener {
            val intent = Intent(this, SpecifyRoleActivity::class.java)
            com.example.androidfa23.intentLauncher.launch(intent)
        }

        val continueButton : Button = findViewById(R.id.continueButton)
        continueButton.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            com.example.androidfa23.intentLauncher.launch(intent)
        }
        com.example.androidfa23.intentLauncher = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()
        ) {}

        val mondayButton : ImageView = findViewById(R.id.monButton)
        val tuesdayButton : ImageView = findViewById(R.id.tueButton)
        val wednesdayButton : ImageView = findViewById(R.id.wedButton)
        val thursdayButton : ImageView = findViewById(R.id.thuButton)
        val fridayButton : ImageView = findViewById(R.id.friButton)
        val saturdayButton : ImageView = findViewById(R.id.satButton)
        val sundayButton : ImageView = findViewById(R.id.sunButton)

        mondayButton.setOnClickListener{onClickHandler()}
        tuesdayButton.setOnClickListener{onClickHandler()}
        wednesdayButton.setOnClickListener{onClickHandler()}
        thursdayButton.setOnClickListener{onClickHandler()}
        fridayButton.setOnClickListener{onClickHandler()}
        saturdayButton.setOnClickListener{onClickHandler()}
        sundayButton.setOnClickListener{onClickHandler()}
    }
    private fun onClickHandler() {
        val newFragment = AvailabilitiesDialogFragment()
        newFragment.show(supportFragmentManager, "Availabilities")
    }
}