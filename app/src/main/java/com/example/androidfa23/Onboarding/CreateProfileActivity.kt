package com.example.androidfa23.Onboarding

import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.cardview.widget.CardView
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.androidfa23.Browse.BrowseOrganizationFragment
import com.example.androidfa23.Browse.OrgRecyclerAdapter
import com.example.androidfa23.Data.OrganizationClass
import com.example.androidfa23.MainActivity
import com.example.androidfa23.R
import com.vmadalin.easypermissions.EasyPermissions
import com.vmadalin.easypermissions.dialogs.SettingsDialog

class CreateProfileActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_profile)

        val data = arrayListOf(
            OrganizationClass(
                id = 1,
                name = "Add Org...",
                desc = ""
            ),
        )

        val recyclerView : RecyclerView = findViewById(R.id.recyclerView)
        val adapter = OrgRecyclerAdapter(data)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = GridLayoutManager(this, 2)

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

        val YButton : ImageView = findViewById(R.id.Y)
        val NButton : ImageView = findViewById(R.id.N)
        YButton.setOnClickListener {
            YButton.setImageResource(R.drawable.y_coffee_chat_selected)
            NButton.setImageResource(R.drawable.n_coffee_chat)
        }
        NButton.setOnClickListener {
            NButton.setImageResource(R.drawable.n_coffee_chat_selected)
            YButton.setImageResource(R.drawable.y_coffee_chat)
        }

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

        //TODO FIX
        val profile : CardView = findViewById(R.id.cardView2)
        profile.setOnClickListener {
            if (!EasyPermissions.hasPermissions(
                    this,
                    android.Manifest.permission.READ_EXTERNAL_STORAGE
                )
            ) {
                EasyPermissions.requestPermissions(
                    host = this,
                    rationale = "Gallery access required to upload photo.",
                    requestCode = 1,
                    perms = arrayOf(android.Manifest.permission.READ_EXTERNAL_STORAGE)
                )
            } else {
                Log.d("LOG", "Permission already granted.")
            }

        }
    }
    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        // EasyPermissions handles the request result as opposed to regular Android
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this)
    }

    fun onPermissionsDenied(requestCode: Int, perms: List<String>) {
        // Check whether the user denied any permissions and checked "NEVER ASK AGAIN."
        // This will display a dialog directing them to enable the permission in app settings.
        if (EasyPermissions.somePermissionPermanentlyDenied(this, perms)) {
            SettingsDialog.Builder(this).build().show()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 1) {
            // Do something after user returned from app settings screen, e.g. check to see
            // if the permissions have been enabled!
        }
    }

    private fun onClickHandler() {
        val newFragment = AvailabilitiesDialogFragment()
        newFragment.show(supportFragmentManager, "Availabilities")
    }
}