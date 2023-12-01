package com.example.androidfa23.Onboarding

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import com.example.androidfa23.Data.Repository
import com.example.androidfa23.MainActivity
import com.example.androidfa23.R
import org.json.JSONObject

class CreateOrganizationActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_organization)

        val continueButton : Button = findViewById(R.id.continueButton)

        val profileImageView: ImageView = findViewById(R.id.profileImageView)
        val backgroundImageView: ImageView = findViewById(R.id.backgroundImageView)


        val email = intent.extras?.getString("username")
        val password = intent.extras?.getString("password")
        val isnew = intent.extras?.getBoolean("new")
        val name: EditText = findViewById(R.id.nameText)
        val bio: EditText = findViewById(R.id.bioText)

        continueButton.setOnClickListener {

            if (isnew!=null){
                val body: JSONObject = JSONObject()
                body.put("name", name.text)
                body.put("login_email", email)
                body.put("login_password", password)

                var instance = Repository(this)

                val body2 = JSONObject()

                body2.put("name", name.text)
                body2.put("pfp", profileImageView.drawable)
                body2.put("pfp", backgroundImageView.drawable)
                body2.put("bio", bio.text)

                instance.postOrg(body, body2)

            }

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