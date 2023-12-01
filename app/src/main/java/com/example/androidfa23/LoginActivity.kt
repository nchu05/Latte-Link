package com.example.androidfa23

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
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
        val emailET: EditText = findViewById(R.id.emailET)
        val password: EditText = findViewById(R.id.passwordET)

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
                    Log.e("JSON", emailET.text.toString())
                    intent.putExtra("username", emailET.text.toString())
                    intent.putExtra("password", password.text.toString())
                    intentLauncher.launch(intent)
                }
                alreadyHaveAccountButton.setOnClickListener(clickListener())
            }
            return null
        }

        createAccountButton.setOnClickListener {
            val intent = Intent(this, SpecifyRoleActivity::class.java)
            Log.e("JSON", emailET.text.toString())
            intent.putExtra("username", emailET.text.toString())
            intent.putExtra("password", password.text.toString())
            intentLauncher.launch(intent)
        }
        alreadyHaveAccountButton.setOnClickListener {clickListener()}

        intentLauncher = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()
        ) {}


    }
}