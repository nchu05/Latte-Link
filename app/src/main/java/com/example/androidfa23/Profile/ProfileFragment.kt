package com.example.androidfa23.Profile

import android.content.ContentValues
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.androidfa23.Browse.OrgRecyclerAdapter
import com.example.androidfa23.Data.IndPersonClass
import com.example.androidfa23.Data.OrganizationClass
import com.example.androidfa23.Data.Repository
import com.example.androidfa23.Onboarding.CreateProfileActivity
import com.example.androidfa23.ProfileTimesAdapter
import com.example.androidfa23.R
import com.google.android.material.internal.ContextUtils
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import okhttp3.Call
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import org.w3c.dom.Text
import java.io.IOException

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [ProfileFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ProfileFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_profile, container, false)

        val recycler : RecyclerView = view.findViewById(R.id.recyclerView)
        val editButton: Button = view.findViewById(R.id.editButton)


        val name: TextView = view.findViewById(R.id.nameText)
        val year: TextView = view.findViewById(R.id.classText)
        val major: TextView = view.findViewById(R.id.majorText)
        val bio: TextView = view.findViewById(R.id.bioText)
        val locations: TextView = view.findViewById(R.id.locationText)
        val email: TextView = view.findViewById(R.id.emailText)
        val linkedin: TextView = view.findViewById(R.id.linkedinText)
        val instagram: TextView = view.findViewById(R.id.instagramText)
        val facebook: TextView = view.findViewById(R.id.facebookText)

        var instance = context?.let { Repository(it) }

        Log.e("JSON USER", instance?.getId().toString())
        val url = "http://35.245.150.19/api/users/${instance?.getId()}"
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
                    ContextUtils.getActivity(context)?.runOnUiThread{
                        name.text = user.name
                        bio.text = user.bio
                        year.text = user.year.toString()
                        major.text = user.major
                        email.text = "Email: " + user.public_email
                        linkedin.text = "LinkedIn: " + user.linkedin
                        instagram.text = "Instagram: " + user.instagram
                        val adapter = OrgRecyclerAdapter(user.organizations)
                        recycler.adapter = adapter
                        recycler.layoutManager = GridLayoutManager(context, 2)
                    }
                }
                Log.e("JSON", "res"+ res)
            }
        })





        editButton.setOnClickListener(){
            val intent1 = Intent(context, CreateProfileActivity::class.java)
            startActivity(intent1)
        }

        val monData : List<String> = listOf("10-10:30 AM", "10:30-11 AM")
        val monRecycler : RecyclerView = view.findViewById(R.id.recyclerMon)
        monRecycler.adapter = ProfileTimesAdapter(monData, childFragmentManager)
        monRecycler.layoutManager = LinearLayoutManager(view.context, LinearLayoutManager.HORIZONTAL, false)

        val tuesData : List<String> = listOf("11-11:30 AM")
        val tuesRecycler : RecyclerView = view.findViewById(R.id.recyclerTues)
        tuesRecycler.adapter = ProfileTimesAdapter(tuesData, childFragmentManager)
        tuesRecycler.layoutManager = LinearLayoutManager(view.context, LinearLayoutManager.HORIZONTAL, false)

        val wedData : List<String> = listOf()
        val wedRecycler : RecyclerView = view.findViewById(R.id.recyclerWed)
        wedRecycler.adapter = ProfileTimesAdapter(wedData, childFragmentManager)
        wedRecycler.layoutManager = LinearLayoutManager(view.context, LinearLayoutManager.HORIZONTAL, false)

        val thurData : List<String> = listOf("1-1:30 PM")
        val thurRecycler : RecyclerView = view.findViewById(R.id.recyclerThu)
        thurRecycler.adapter = ProfileTimesAdapter(thurData, childFragmentManager)
        thurRecycler.layoutManager = LinearLayoutManager(view.context, LinearLayoutManager.HORIZONTAL, false)

        val friData : List<String> = listOf()
        val friRecycler : RecyclerView = view.findViewById(R.id.recyclerFri)
        friRecycler.adapter = ProfileTimesAdapter(friData, childFragmentManager)
        friRecycler.layoutManager = LinearLayoutManager(view.context, LinearLayoutManager.HORIZONTAL, false)

        val satData : List<String> = listOf("10-10:30 AM", "10:30-11 AM", "11-11:30 AM", "11:30-12 PM")
        val satRecycler : RecyclerView = view.findViewById(R.id.recyclerSat)
        satRecycler.adapter = ProfileTimesAdapter(satData, childFragmentManager)
        satRecycler.layoutManager = LinearLayoutManager(view.context, LinearLayoutManager.HORIZONTAL, false)

        val sunData : List<String> = listOf()
        val sunRecycler : RecyclerView = view.findViewById(R.id.recyclerSun)
        sunRecycler.adapter = ProfileTimesAdapter(sunData, childFragmentManager)
        sunRecycler.layoutManager = LinearLayoutManager(view.context, LinearLayoutManager.HORIZONTAL, false)


//        val data = arrayListOf(
//            OrganizationClass(
//                id = 1,
//                name = "Org 1",
//                org_pfp=""
//            ),
//            OrganizationClass(
//                id = 2,
//                name = "Org 2",
//                org_pfp=""
//            ),
//        )
//        repeat(2){
//            data.addAll(data)
//        }
//        val adapter = OrgRecyclerAdapter(data)
//        recycler.adapter = adapter
        recycler.layoutManager = GridLayoutManager(context, 2)

        return view
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

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment ProfileFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            ProfileFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}