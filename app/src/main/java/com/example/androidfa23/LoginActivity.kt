package com.example.androidfa23

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import com.example.androidfa23.Onboarding.SpecifyRoleActivity

lateinit var intentLauncher: ActivityResultLauncher<Intent>
class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val createAccountButton : ImageView = findViewById(R.id.createAccountButton)
        val alreadyHaveAccountButton : Button = findViewById(R.id.alreadyHaveAccountButton)

        fun clickListener(): View.OnClickListener? {
            createAccountButton.setImageResource(R.drawable.sign_in_button)
            alreadyHaveAccountButton.setText(R.string.dont_have_account)
            createAccountButton.setOnClickListener{
                val intent = Intent(this, MainActivity::class.java)
                intentLauncher.launch(intent)
            }
            alreadyHaveAccountButton.setOnClickListener{
                createAccountButton.setImageResource(R.drawable.create_account_button)
                alreadyHaveAccountButton.setText(R.string.i_already_have_an_account)
                createAccountButton.setOnClickListener{
                    val intent = Intent(this, SpecifyRoleActivity::class.java)
                    intentLauncher.launch(intent)
                }
                alreadyHaveAccountButton.setOnClickListener(clickListener())
            }
            return null
        }
        //TODO fix

        createAccountButton.setOnClickListener {
            val intent = Intent(this, SpecifyRoleActivity::class.java)
            intentLauncher.launch(intent)
        }
        alreadyHaveAccountButton.setOnClickListener {clickListener()}

        intentLauncher = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()
        ) {}


    }
}