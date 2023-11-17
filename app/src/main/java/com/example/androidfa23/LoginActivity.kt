package com.example.androidfa23

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts

private lateinit var intentLauncher: ActivityResultLauncher<Intent>
class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val joinButton : Button = findViewById(R.id.joinButton)
        joinButton.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            intentLauncher.launch(intent)
        }
        intentLauncher = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()
        ) {}
    }
}