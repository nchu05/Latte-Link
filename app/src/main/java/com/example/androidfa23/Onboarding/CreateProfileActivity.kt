package com.example.androidfa23.Onboarding

import android.Manifest.permission.READ_EXTERNAL_STORAGE
import android.Manifest.permission.READ_MEDIA_IMAGES
import android.Manifest.permission.READ_MEDIA_VIDEO
import android.Manifest.permission.READ_MEDIA_VISUAL_USER_SELECTED
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.core.content.PermissionChecker.PERMISSION_GRANTED
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.androidfa23.Browse.OrgRecyclerAdapter
import com.example.androidfa23.Data.OrganizationClass
import com.example.androidfa23.Data.Repository
import com.example.androidfa23.MainActivity
import com.example.androidfa23.R
import com.example.androidfa23.WeeklyAvailabilitiesAdapter
import org.json.JSONObject
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.launch

class CreateProfileActivity : AppCompatActivity() {
    lateinit var profileImageView : ImageView
    lateinit var backgroundImageView: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_profile)

        val data : List<OrganizationClass> = arrayListOf()
        profileImageView = findViewById(R.id.profileImageView)
        backgroundImageView = findViewById(R.id.backgroundImageView)
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

        val requestPermissions = registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions()) { results ->
            if (
                Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU &&
                (
                        ContextCompat.checkSelfPermission(this, READ_MEDIA_IMAGES) == PERMISSION_GRANTED ||
                                ContextCompat.checkSelfPermission(this, READ_MEDIA_VIDEO) == PERMISSION_GRANTED
                        )
            ) {
                val intent = Intent(MediaStore.ACTION_PICK_IMAGES)
                // starting activity on below line.
                startActivityForResult(intent, 1)
            } else if (
                Build.VERSION.SDK_INT >= Build.VERSION_CODES.UPSIDE_DOWN_CAKE &&
                ContextCompat.checkSelfPermission(this, READ_MEDIA_VISUAL_USER_SELECTED) == PERMISSION_GRANTED
            ) {
                val intent = Intent(MediaStore.ACTION_PICK_IMAGES)
                // starting activity on below line.
                startActivityForResult(intent, 1)
            }  else if (ContextCompat.checkSelfPermission(this, READ_EXTERNAL_STORAGE) == PERMISSION_GRANTED) {
                val intent = Intent(MediaStore.ACTION_PICK_IMAGES)
                // starting activity on below line.
                startActivityForResult(intent, 1)
            } else {
                Toast.makeText(this, "Permission required to upload images.", Toast.LENGTH_SHORT).show()
            }
        }
        val profile : CardView = findViewById(R.id.cardView2)
        profile.setOnClickListener {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.UPSIDE_DOWN_CAKE) {
                requestPermissions.launch(arrayOf(READ_MEDIA_IMAGES, READ_MEDIA_VIDEO, READ_MEDIA_VISUAL_USER_SELECTED))
            } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                requestPermissions.launch(arrayOf(READ_MEDIA_IMAGES, READ_MEDIA_VIDEO))
            } else {
                requestPermissions.launch(arrayOf(READ_EXTERNAL_STORAGE))
            }

        }
        val background : CardView = findViewById(R.id.cardView3)
        background.setOnClickListener {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.UPSIDE_DOWN_CAKE) {
                requestPermissions.launch(arrayOf(READ_MEDIA_IMAGES, READ_MEDIA_VIDEO, READ_MEDIA_VISUAL_USER_SELECTED))
            } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                requestPermissions.launch(arrayOf(READ_MEDIA_IMAGES, READ_MEDIA_VIDEO))
            } else {
                requestPermissions.launch(arrayOf(READ_EXTERNAL_STORAGE))
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

                val body1: JSONObject = JSONObject()
                body1.put("year", year.text)
                body1.put("major", major.text)
                body1.put("pfp", profileImageView.drawable)
                body1.put("bio", year.text)
                body1.put("email", email1.text)
                body1.put("banner", backgroundImageView.drawable)
                body1.put("instagram", instagram.text)
                body1.put("linkedin", linkedIn.text)

                instance.postPerson(body, body1)
            }
            val intent = Intent(this, MainActivity::class.java)

            com.example.androidfa23.intentLauncher.launch(intent)
        }

    }

    private fun onClickHandler(day: String) {
        val newFragment = AvailabilitiesDialogFragment(day)
        newFragment.show(supportFragmentManager, "Availabilities")
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode === RESULT_OK) {
            // compare the resultCode with the
            // constant
            if (requestCode === 1) {
                // Get the url of the image from data
                val selectedImageUri: Uri = data?.data!!
                if (null != selectedImageUri) {
                    // update the image view in the layout
                    profileImageView.setImageURI(selectedImageUri)
                }
            }
        }
    }
}