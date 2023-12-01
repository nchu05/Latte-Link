package com.example.androidfa23.Browse

import android.content.ContentValues
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.androidfa23.Data.OrganizationClass
import com.example.androidfa23.Data.PersonClass
import com.example.androidfa23.R
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import okhttp3.Call
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import java.io.IOException

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [BrowsePeopleFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class BrowsePeopleFragment : Fragment() {
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
        val view =  inflater.inflate(R.layout.fragment_people, container, false)
        val recycler : RecyclerView = view.findViewById(R.id.recyclerView)
        val url = "http://35.245.150.19/api/users/"
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
                var peopleList = parsePeople(res)
                if (peopleList!=null){
                    val adapter = PeopleRecyclerAdapter(peopleList)
                    getActivity()?.runOnUiThread{
                        recycler.adapter = adapter
                        recycler.layoutManager = GridLayoutManager(context, 2)
                    }
                }
                Log.e("JSON", "res"+ res)
            }
        })
        return view
    }


    private fun parsePeople(res : String?): List<PersonClass>? {
        try{
            val moshi = Moshi.Builder().addLast(com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory()).build()
            val noteListType = Types.newParameterizedType(List::class.java, PersonClass::class.java)
            val jsonAdapter: JsonAdapter<List<PersonClass>> = moshi.adapter(noteListType)
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
         * @return A new instance of fragment PeopleFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            BrowsePeopleFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}