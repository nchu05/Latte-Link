package com.example.androidfa23.Browse

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.androidfa23.Data.OrganizationClass
import com.example.androidfa23.MainActivity
import com.example.androidfa23.Onboarding.CreateProfileActivity
import com.example.androidfa23.R

class IndividualPersonActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_individual_person)

        val recycler : RecyclerView = findViewById(R.id.recyclerView)

        val backButton : ImageView = findViewById(R.id.backButton)
        backButton.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        val data = arrayListOf(
            OrganizationClass(
                id = 1,
                name = "Org 1",
                org_pfp=""
            ),
            OrganizationClass(
                id = 2,
                name = "Org 2",
                org_pfp=""
            ),
        )
        repeat(2){
            data.addAll(data)
        }
        val adapter = OrgRecyclerAdapter(data)
        recycler.adapter = adapter
        recycler.layoutManager = GridLayoutManager(this, 2)
    }
}