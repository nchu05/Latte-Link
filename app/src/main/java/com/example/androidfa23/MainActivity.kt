package com.example.androidfa23

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.androidfa23.Browse.BrowseFragment
import com.example.androidfa23.Profile.ProfileFragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val bottomNavigationView : BottomNavigationView = findViewById(R.id.bottomNavigationView)

        //if not signed in
            //redirect to sign in page
        //else
            //the code below

        bottomNavigationView.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.browse -> {
                    supportFragmentManager.beginTransaction().
                    replace(R.id.fragmentContainerView, BrowseFragment.newInstance("", ""))
                        .commit()
                }
                R.id.message -> {
                    supportFragmentManager.beginTransaction().
                    replace(R.id.fragmentContainerView, MessageFragment.newInstance("", ""))
                        .commit()
                }
                R.id.calendar -> {
                    supportFragmentManager.beginTransaction().
                    replace(R.id.fragmentContainerView, CalendarFragment.newInstance("", ""))
                        .commit()
                }
                R.id.profile -> {
                    supportFragmentManager.beginTransaction().
                    replace(R.id.fragmentContainerView, ProfileFragment.newInstance("", ""))
                        .commit()
                }
            }
            true
        }
    }
}