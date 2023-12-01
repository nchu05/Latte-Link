package com.example.androidfa23.Browse

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.androidfa23.Data.OrganizationClass
import com.example.androidfa23.R
import com.example.androidfa23.Data.Repository
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import java.util.Locale

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [BrowseOrganizationFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class BrowseOrganizationFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private var orgsList: MutableList<OrganizationClass> = mutableListOf()
    private lateinit var recycler : RecyclerView

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
        val view =  inflater.inflate(R.layout.fragment_organization, container, false)
        recycler = view.findViewById(R.id.recyclerView)
        val adapter = OrgRecyclerAdapter(orgsList)
        recycler.adapter = adapter
        recycler.layoutManager = GridLayoutManager(context, 2)
        val repository = Repository(view.context)
        displayOrgs(repository.fetchAllOrgs(), adapter)
        return view
    }

    private fun displayOrgs(res : String?, adapter: OrgRecyclerAdapter?) {
        if (res != "") {
            val moshi = Moshi.Builder().addLast(KotlinJsonAdapterFactory()).build()
            val noteListType = Types.newParameterizedType(List::class.java, OrganizationClass::class.java)
            val jsonAdapter: JsonAdapter<List<OrganizationClass>> = moshi.adapter(noteListType)
            val parsedOrgs =  jsonAdapter.fromJson(res)
            if (parsedOrgs != null) {
                for (current : OrganizationClass in parsedOrgs) {
                    orgsList.add(current)
                    adapter?.notifyItemInserted(orgsList.size-1)
                }
            }
        }
    }

    private fun onQueryChanged(filterQuery: String): List<OrganizationClass> {
        val filteredList = ArrayList<OrganizationClass>()
        for (currentOrg in orgsList) {
            if (currentOrg.name.lowercase(Locale.getDefault()).contains(filterQuery)) {
                filteredList.add(currentOrg)
            }
        }
        return filteredList
    }

    fun filterWithQuery(query: String) {
        if (query.isNotEmpty()) {
            val filteredList: List<OrganizationClass> = onQueryChanged(query)
            val adapter = OrgRecyclerAdapter(filteredList)
            recycler.adapter = adapter
        }
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment OrganizationFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            BrowseOrganizationFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}