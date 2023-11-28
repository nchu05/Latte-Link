package com.example.androidfa23

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.androidfa23.Browse.OrgRecyclerAdapter
import com.example.androidfa23.Data.OrganizationClass
import com.example.androidfa23.Data.PersonClass
import com.example.androidfa23.Data.RequestClass

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [RequestFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class RequestFragment : Fragment() {
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
            RequestClass(
                id = 1,
                requester = PersonClass(id = 2, name = "Person 1"),
                receiver = PersonClass(id = 2, name = "Person 2"),
                dateTime = "Monday, Oct 27th",
                location = "Duffield Hall",
                message = "I would love to coffee chat you!"
            ),
            RequestClass(
                id = 1,
                requester = PersonClass(id = 2, name = "Person 1"),
                receiver = PersonClass(id = 2, name = "Person 2"),
                dateTime = "Monday, Oct 27th",
                location = "Duffield Hall",
                message = "I would love to coffee chat you!"
            ),
            RequestClass(
                id = 1,
                requester = PersonClass(id = 2, name = "Person 1"),
                receiver = PersonClass(id = 2, name = "Person 2"),
                dateTime = "Monday, Oct 27th",
                location = "Duffield Hall",
                message = "I would love to coffee chat you!"
            ),
            RequestClass(
                id = 1,
                requester = PersonClass(id = 2, name = "Person 1"),
                receiver = PersonClass(id = 2, name = "Person 2"),
                dateTime = "Monday, Oct 27th",
                location = "Duffield Hall",
                message = "I would love to coffee chat you!"
            ),

            )
        repeat(4){
            data.addAll(data)
        }
        //add correct recycleradapter
        //recycler.adapter = adapter
        //recycler.layoutManager = GridLayoutManager(context, 2)

        return view
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment RequestFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            RequestFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}