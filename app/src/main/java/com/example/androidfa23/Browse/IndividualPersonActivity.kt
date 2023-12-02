package com.example.androidfa23.Browse

import android.content.ContentValues
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.androidfa23.Data.IndOrgClass
import com.example.androidfa23.Data.IndPersonClass
import com.example.androidfa23.Data.OrganizationClass
import com.example.androidfa23.Data.Repository
import com.example.androidfa23.MainActivity
import com.example.androidfa23.Onboarding.CreateProfileActivity
import com.example.androidfa23.R
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


        val name: TextView = findViewById(R.id.nameText)
        val year: TextView = findViewById(R.id.classText)
        val major: TextView = findViewById(R.id.majorText)
        val bio: TextView = findViewById(R.id.bioText)
        val recycler : RecyclerView = findViewById(R.id.recyclerView)
        val email: TextView = findViewById(R.id.emailText)
        val linkedin: TextView = findViewById(R.id.linkedinText)
        val instagram: TextView = findViewById(R.id.instagramText)
        val facebook: TextView = findViewById(R.id.facebookText)


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
                var user = parsePerson(res)
                if (user!=null){
                    ContextUtils.getActivity(this@IndividualPersonActivity)?.runOnUiThread{
                        name.text = user.name
                        bio.text = user.bio
                        year.text = user.year.toString()
                        major.text = user.major
                        email.text = "Email: " + user.public_email
                        linkedin.text = "LinkedIn: " + user.linkedin
                        instagram.text = "Instagram: " + user.instagram
                        val recycler : RecyclerView = findViewById(R.id.recyclerView)
                        val adapter = OrgRecyclerAdapter(user.organizations)
                        recycler.adapter = adapter
                        recycler.layoutManager = GridLayoutManager(this@IndividualPersonActivity, 2)
                    }
                }
                Log.e("JSON", "res"+ res)
            }
        })
    }

    private fun parsePerson(res : String?): IndPersonClass? {
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