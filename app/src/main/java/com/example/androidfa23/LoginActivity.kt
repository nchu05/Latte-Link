package com.example.androidfa23

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.view.isVisible
import com.example.androidfa23.Onboarding.OnboardingActivity

lateinit var intentLauncher: ActivityResultLauncher<Intent>
class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val joinButton : Button = findViewById(R.id.joinButton)
        val alreadyHaveAccountButton : Button = findViewById(R.id.alreadyHaveAccountButton)

        fun clickListener(): View.OnClickListener? {
            joinButton.setText(R.string.log_in)
            alreadyHaveAccountButton.setText(R.string.dont_have_account)
            joinButton.setOnClickListener{
                val intent = Intent(this, MainActivity::class.java)
                intentLauncher.launch(intent)
            }
            alreadyHaveAccountButton.setOnClickListener{
                joinButton.setText(R.string.join)
                alreadyHaveAccountButton.setText(R.string.i_already_have_an_account)
                joinButton.setOnClickListener{
                    val intent = Intent(this, OnboardingActivity::class.java)
                    intentLauncher.launch(intent)
                }
                alreadyHaveAccountButton.setOnClickListener(clickListener())
            }
            return null
        }
        //TODO fix

        joinButton.setOnClickListener {
            val intent = Intent(this, OnboardingActivity::class.java)
            intentLauncher.launch(intent)
        }
        alreadyHaveAccountButton.setOnClickListener {clickListener()}

        intentLauncher = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()
        ) {}


    }
}