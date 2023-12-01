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
import com.example.androidfa23.Data.OrganizationClass
import com.example.androidfa23.Data.PersonClass
import com.example.androidfa23.MainActivity
import com.example.androidfa23.R
import com.google.android.material.internal.ContextUtils.getActivity
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import okhttp3.Call
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import org.w3c.dom.Text
import java.io.IOException

class IndividualOrganizationActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_individual_organization)

        val id = intent.extras?.getString("id")

        val backButton : ImageView = findViewById(R.id.backButton)
        backButton.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        val name: TextView = findViewById(R.id.organizationName)
        val bio: TextView = findViewById(R.id.bioText)
        val tag1: TextView = findViewById(R.id.tag1)
        val tag2: TextView = findViewById(R.id.tag2)
        val tag3: TextView = findViewById(R.id.tag3)
        val url = "http://35.245.150.19/api/orgs/${id}"
        Log.e("JSON", url)
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
                    getActivity(this@IndividualOrganizationActivity)?.runOnUiThread{
                        name.text = org.name
                        bio.text = org.bio
                        val recycler : RecyclerView = findViewById(R.id.recyclerView)
                        val adapter = MembersRecyclerAdapter(org.users)
                        recycler.adapter = adapter
                        recycler.layoutManager = GridLayoutManager(this@IndividualOrganizationActivity, 2)
                    }
                }
                Log.e("JSON", "res"+ res)
            }
        })

    }

    private fun parseOrg(res : String?): IndOrgClass? {
        try{
            val moshi = Moshi.Builder().addLast(com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory()).build()
            val jsonAdapter: JsonAdapter<IndOrgClass> = moshi.adapter(IndOrgClass::class.java)
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