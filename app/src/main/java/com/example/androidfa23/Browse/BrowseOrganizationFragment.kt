package com.example.androidfa23.Browse

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.androidfa23.Data.OrganizationClass
import com.example.androidfa23.R

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
        val recycler : RecyclerView = view.findViewById(R.id.recyclerView)
        val data = arrayListOf(
            OrganizationClass(
                id = 1,
                name = "Org 1",
            ),
            OrganizationClass(
                id = 2,
                name = "Org 2",
            ),
            OrganizationClass(
                id = 3,
                name = "Org 3",
            ),
            OrganizationClass(
                id = 4,
                name = "Org 4",
            ),
        )
        repeat(4){
            data.addAll(data)
        }
        val adapter = OrgRecyclerAdapter(data)
        recycler.adapter = adapter
        recycler.layoutManager = GridLayoutManager(context, 2)
        return view
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