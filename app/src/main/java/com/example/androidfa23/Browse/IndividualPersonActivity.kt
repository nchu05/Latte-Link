package com.example.androidfa23.Browse

import android.content.ContentValues
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.androidfa23.Data.IndOrgClass
import com.example.androidfa23.Data.IndPersonClass
import com.example.androidfa23.Data.OrganizationClass
import com.example.androidfa23.MainActivity
import com.example.androidfa23.Onboarding.CreateProfileActivity
import com.example.androidfa23.ProfileTimesAdapter
import com.example.androidfa23.R
import com.example.androidfa23.WeeklyAvailabilitiesAdapter
import com.google.android.material.internal.ContextUtils
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import okhttp3.Call
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import java.io.IOException

class IndividualPersonActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_individual_person)

        val id = intent.extras?.getString("id")

        val backButton : ImageView = findViewById(R.id.backButton)
        backButton.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
        val fragmentManager : FragmentManager = supportFragmentManager

        val name: TextView = findViewById(R.id.nameText)
        val year: TextView = findViewById(R.id.classText)
        val major: TextView = findViewById(R.id.majorText)
        val bio: TextView = findViewById(R.id.bioText)
        val recycler : RecyclerView = findViewById(R.id.recyclerView)
        val email: TextView = findViewById(R.id.emailText)
        val linkedin: TextView = findViewById(R.id.linkedinText)
        val instagram: TextView = findViewById(R.id.instagramText)
        val facebook: TextView = findViewById(R.id.facebookText)

        val monData : List<String> = listOf("10-10:30 AM", "10:30-11 AM")
        val monRecycler : RecyclerView = findViewById(R.id.recyclerMon)
        monRecycler.adapter = ProfileTimesAdapter(monData, fragmentManager)
        monRecycler.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)

        val tuesData : List<String> = listOf("11-11:30 AM")
        val tuesRecycler : RecyclerView = findViewById(R.id.recyclerTues)
        tuesRecycler.adapter = ProfileTimesAdapter(tuesData, fragmentManager)
        tuesRecycler.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)

        val wedData : List<String> = listOf()
        val wedRecycler : RecyclerView = findViewById(R.id.recyclerWed)
        wedRecycler.adapter = ProfileTimesAdapter(wedData, fragmentManager)
        wedRecycler.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)

        val thurData : List<String> = listOf("1-1:30 PM")
        val thurRecycler : RecyclerView = findViewById(R.id.recyclerThu)
        thurRecycler.adapter = ProfileTimesAdapter(thurData, fragmentManager)
        thurRecycler.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)

        val friData : List<String> = listOf()
        val friRecycler : RecyclerView = findViewById(R.id.recyclerFri)
        friRecycler.adapter = ProfileTimesAdapter(friData, fragmentManager)
        friRecycler.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)

        val satData : List<String> = listOf("10-10:30 AM", "10:30-11 AM", "11-11:30 AM", "11:30-12 PM")
        val satRecycler : RecyclerView = findViewById(R.id.recyclerSat)
        satRecycler.adapter = ProfileTimesAdapter(satData, fragmentManager)
        satRecycler.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)

        val sunData : List<String> = listOf()
        val sunRecycler : RecyclerView = findViewById(R.id.recyclerSun)
        sunRecycler.adapter = ProfileTimesAdapter(sunData, fragmentManager)
        sunRecycler.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)

        val url = "http://35.245.150.19/api/users/${id}"
        val client = OkHttpClient()
        val request = Request.Builder().url(url).get().build()

        val response = client.newCall(request).enqueue(object :okhttp3.Callback{
            override fun onFailure(call: Call, e: IOException) {
                Log.e(ContentValues.TAG, "onFailure: Failed")
            }

            override fun onResponse(call: Call, response: Response) {
                Log.d(ContentValues.TAG, "Success!")
                val res = response.body?.string()
                Log.e("JSON", "res"+ res)
                var org = parseOrg(res)
                if (org!=null){
                    ContextUtils.getActivity(this@IndividualPersonActivity)?.runOnUiThread{
                        name.text = org.name
                        bio.text = org.bio
                        year.text = org.year.toString()
                        major.text = org.major
                        email.text = "Email: " + org.public_email
                        linkedin.text = "LinkedIn: " + org.linkedin
                        instagram.text = "Instagram: " + org.instagram
                        val recycler : RecyclerView = findViewById(R.id.recyclerView)
                        val adapter = OrgRecyclerAdapter(org.organizations)
                        recycler.adapter = adapter
                        recycler.layoutManager = GridLayoutManager(this@IndividualPersonActivity, 2)
                    }
                }
                Log.e("JSON", "res"+ res)
            }
        })
    }

    private fun parseOrg(res : String?): IndPersonClass? {
        try{
            val moshi = Moshi.Builder().addLast(com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory()).build()
            val jsonAdapter: JsonAdapter<IndPersonClass> = moshi.adapter(IndPersonClass::class.java)
            val parsedOrgs =  jsonAdapter.fromJson(res)
            Log.e("JSON", "success")
            Log.e("JSON", parsedOrgs.toString())
            return parsedOrgs
        }catch (x:Exception){
            Log.e("error", x.toString())
            return null
        }
    }

}