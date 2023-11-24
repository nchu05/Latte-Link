package com.example.androidfa23.Browse

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.androidfa23.Data.PersonClass
import com.example.androidfa23.MainActivity
import com.example.androidfa23.R

class IndividualOrganizationActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_individual_organization)

        val backButton : ImageView = findViewById(R.id.backButton)
        backButton.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        val recycler : RecyclerView = findViewById(R.id.recyclerView)
        val data = arrayListOf(
            PersonClass(
                id = 1,
                name = "Person 1",
            ),
            PersonClass(
                id = 2,
                name = "Person 2",
            ),
            PersonClass(
                id = 3,
                name = "Person 3",
            ),
            PersonClass(
                id = 4,
                name = "Person 4",
            ),
        )
        repeat(4){
            data.addAll(data)
        }
        val adapter = MembersRecyclerAdapter(data)
        recycler.adapter = adapter
        recycler.layoutManager = GridLayoutManager(this, 2)
    }
}