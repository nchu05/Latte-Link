package com.example.androidfa23

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.androidfa23.Data.PersonClass
import com.example.androidfa23.Data.RequestClass

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [CalendarFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class CalendarFragment : Fragment() {
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
        val view = inflater.inflate(R.layout.fragment_calendar, container, false)

        val recycler : RecyclerView = view.findViewById(R.id.recyclerView)

        val data1 = arrayListOf(
            Pair("Mon, Dec 4th", arrayListOf(
            RequestClass(
                id = 1,
                requester = PersonClass(id = 2, name = "Person 1", position = "Position"),
                receiver = PersonClass(id = 2, name = "Person 2", position = "Position"),
                dateTime = "Monday, Oct 27th",
                location = "Duffield Hall",
                message = "I would love to coffee chat you!",
                accepted = true
            ),
            RequestClass(
                id = 1,
                requester = PersonClass(id = 2, name = "Person 1", position = "Position"),
                receiver = PersonClass(id = 2, name = "Person 2", position = "Position"),
                dateTime = "Monday, Oct 27th",
                location = "Duffield Hall",
                message = "I would love to coffee chat you!",
                accepted = false
            )
        )
        ),
            Pair("Tue, Dec 5th", arrayListOf(
                RequestClass(
                    id = 1,
                    requester = PersonClass(id = 2, name = "Person 1", position = "Position"),
                    receiver = PersonClass(id = 2, name = "Person 2", position = "Position"),
                    dateTime = "Monday, Oct 27th",
                    location = "Duffield Hall",
                    message = "I would love to coffee chat you!",
                    accepted = true
                ),
                RequestClass(
                    id = 1,
                    requester = PersonClass(id = 2, name = "Person 1", position = "Position"),
                    receiver = PersonClass(id = 2, name = "Person 2", position = "Position"),
                    dateTime = "Monday, Oct 27th",
                    location = "Duffield Hall",
                    message = "I would love to coffee chat you!",
                    accepted = false
                ),
                RequestClass(
                    id = 1,
                    requester = PersonClass(id = 2, name = "Person 1", position = "Position"),
                    receiver = PersonClass(id = 2, name = "Person 2", position = "Position"),
                    dateTime = "Monday, Oct 27th",
                    location = "Duffield Hall",
                    message = "I would love to coffee chat you!",
                    accepted = false
                )
            )
            ),
            Pair("Wed, Dec 6th", arrayListOf(
                RequestClass(
                    id = 1,
                    requester = PersonClass(id = 2, name = "Person 1", position = "Position"),
                    receiver = PersonClass(id = 2, name = "Person 2", position = "Position"),
                    dateTime = "Monday, Oct 27th",
                    location = "Duffield Hall",
                    message = "I would love to coffee chat you!",
                    accepted = true
                ),
                RequestClass(
                    id = 1,
                    requester = PersonClass(id = 2, name = "Person 1", position = "Position"),
                    receiver = PersonClass(id = 2, name = "Person 2", position = "Position"),
                    dateTime = "Monday, Oct 27th",
                    location = "Duffield Hall",
                    message = "I would love to coffee chat you!",
                    accepted = false
                )
            )
            )
        )
        repeat(4){
            data1.addAll(data1)
        }
        val adapter = RequestGroupRecyclerAdapter(data1)
        recycler.adapter = adapter
        recycler.layoutManager = LinearLayoutManager(context)

        return view
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment CalendarFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            CalendarFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}