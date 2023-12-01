package com.example.androidfa23.Onboarding

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.LayoutManager
import com.example.androidfa23.AvailabilitiesAdapter
import com.example.androidfa23.Browse.OrgRecyclerAdapter
import com.example.androidfa23.Data.OrganizationClass
import com.example.androidfa23.Data.Repository
import com.example.androidfa23.MainActivity
import com.example.androidfa23.R
import com.example.androidfa23.WeeklyAvailabilitiesAdapter
import com.vmadalin.easypermissions.EasyPermissions
import com.vmadalin.easypermissions.dialogs.SettingsDialog
import org.json.JSONObject

class CreateProfileActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_profile)

        val data : List<OrganizationClass> = arrayListOf()

        val email = intent.extras?.getString("username")
        val password = intent.extras?.getString("password")
        val isnew = intent.extras?.getBoolean("new")
        val card : CardView = findViewById(R.id.card)
        card.setOnClickListener{
            val newFragment = AddInvolvementsDialogFragment()
            newFragment.show(supportFragmentManager, "AddInvolvements")
        }

        val recyclerView : RecyclerView = findViewById(R.id.recyclerView)
        val adapter = OrgRecyclerAdapter(data)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = GridLayoutManager(this, 2)

        val monData : List<String> = listOf("10-10:30 AM", "10:30-11 AM")
        val monRecycler : RecyclerView = findViewById(R.id.recyclerMon)
        monRecycler.adapter = WeeklyAvailabilitiesAdapter(monData)
        monRecycler.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)

        val tuesData : List<String> = listOf("11-11:30 AM")
        val tuesRecycler : RecyclerView = findViewById(R.id.recyclerTues)
        tuesRecycler.adapter = WeeklyAvailabilitiesAdapter(tuesData)
        tuesRecycler.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)

        val wedData : List<String> = listOf()
        val wedRecycler : RecyclerView = findViewById(R.id.recyclerWed)
        wedRecycler.adapter = WeeklyAvailabilitiesAdapter(wedData)
        wedRecycler.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)

        val thurData : List<String> = listOf("1-1:30 PM")
        val thurRecycler : RecyclerView = findViewById(R.id.recyclerThu)
        thurRecycler.adapter = WeeklyAvailabilitiesAdapter(thurData)
        thurRecycler.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)

        val friData : List<String> = listOf()
        val friRecycler : RecyclerView = findViewById(R.id.recyclerFri)
        friRecycler.adapter = WeeklyAvailabilitiesAdapter(friData)
        friRecycler.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)

        val satData : List<String> = listOf("10-10:30 AM", "10:30-11 AM", "11-11:30 AM", "11:30-12 PM")
        val satRecycler : RecyclerView = findViewById(R.id.recyclerSat)
        satRecycler.adapter = WeeklyAvailabilitiesAdapter(satData)
        satRecycler.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)

        val sunData : List<String> = listOf()
        val sunRecycler : RecyclerView = findViewById(R.id.recyclerSun)
        sunRecycler.adapter = WeeklyAvailabilitiesAdapter(sunData)
        sunRecycler.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)

        val backButton : ImageView = findViewById(R.id.backButton)
        backButton.setOnClickListener {
            val intent = Intent(this, SpecifyRoleActivity::class.java)
            com.example.androidfa23.intentLauncher.launch(intent)
        }

        val continueButton : Button = findViewById(R.id.continueButton)

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

        mondayButton.setOnClickListener{onClickHandler("Monday")}
        tuesdayButton.setOnClickListener{onClickHandler("Tuesday")}
        wednesdayButton.setOnClickListener{onClickHandler("Wednesday")}
        thursdayButton.setOnClickListener{onClickHandler("Thursday")}
        fridayButton.setOnClickListener{onClickHandler("Friday")}
        saturdayButton.setOnClickListener{onClickHandler("Saturday")}
        sundayButton.setOnClickListener{onClickHandler("Sunday")}

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

        val name: EditText = findViewById(R.id.nameText)
        val year: EditText = findViewById(R.id.classText)
        val major: EditText = findViewById(R.id.majorText)
        val bio: EditText = findViewById(R.id.bioText)
        val email1: EditText = findViewById(R.id.emailET)
        val instagram: EditText = findViewById(R.id.instagramET)
        val linkedIn: EditText = findViewById(R.id.linkedInET)

        continueButton.setOnClickListener {
            if (isnew!=null && isnew==true){
                val body: JSONObject = JSONObject()
                body.put("name", name.text)
                body.put("login_email", email)
                body.put("login_password", password)

                var instance = Repository(this)

                var index = instance.postPerson(body)

                val body1: JSONObject = JSONObject()
                body1.put("year", year.text)
                body1.put("major", major.text)
                body1.put("pfp", null)
                body1.put("bio", year.text)
                body1.put("email", email1.text)
                body1.put("banner", null)
                body1.put("instagram", instagram.text)
                body1.put("linkedin", linkedIn.text)
                //val str = instance.updatePerson(body1, index)
            }
            val intent = Intent(this, MainActivity::class.java)

            com.example.androidfa23.intentLauncher.launch(intent)
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

    private fun onClickHandler(day: String) {
        val newFragment = AvailabilitiesDialogFragment(day)
        newFragment.show(supportFragmentManager, "Availabilities")
    }
}