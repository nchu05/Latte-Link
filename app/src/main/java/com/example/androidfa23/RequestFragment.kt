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
 * Use the [RequestFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class RequestFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: Int? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getInt(ARG_PARAM1)
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
                sender = PersonClass(id = 2, name = "Person 1",
                    pfp=""),
                receiver = PersonClass(id = 2, name = "Person 2",
                    pfp=""),
                dateTime = "Monday, Oct 27th",
                location = "Duffield Hall",
                message = "I would love to coffee chat you!",
                accepted = true
            ),
            RequestClass(
                id = 1,
                sender = PersonClass(id = 2, name = "Person 1",
                    pfp=""),
                receiver = PersonClass(id = 2, name = "Person 2",
                    pfp=""),
                dateTime = "Monday, Oct 27th",
                location = "Duffield Hall",
                message = "I would love to coffee chat you!",
                accepted = false
            ),
            RequestClass(
                id = 1,
                sender = PersonClass(id = 2, name = "Person 1",
                    pfp=""),
                receiver = PersonClass(id = 2, name = "Person 2",
                    pfp=""),
                dateTime = "Monday, Oct 27th",
                location = "Duffield Hall",
                message = "Dear Alex,\n\nI hope this message finds you well. My name is Damien Pieter, and I’m a freshman studying Industrial and Labor Relations. I would like to hear more about your role as a Wealth Management Intern at Baxter Investment Management. I am personally not very informed on what wealth management is specifically, so a brief overview of the career path would be greatly appreciated. Further I am curious about how that experience pushed you toward consulting and private equity. Through this chat, I hope to gain a better understanding of how your career interests have shifted and been further solidified. As I am searching for potential freshman internships, I would appreciated any advice you have for this process.I look forward to speaking with you!\n\nBest,\nMax",
                accepted = null
            ),
            RequestClass(
                id = 1,
                sender = PersonClass(id = 2, name = "Person 1",
                    pfp=""),
                receiver = PersonClass(id = 2, name = "Person 2",
                    pfp=""),
                dateTime = "Monday, Oct 27th",
                location = "Duffield Hall",
                message = "I would love to coffee chat you!",
                accepted = true
            ),

            )
        repeat(4){
            data.addAll(data)
        }



        if (param1==0){
            val adapter = ReceivedRecyclerAdapter(data)
            recycler.adapter = adapter
            recycler.layoutManager = LinearLayoutManager(context)
        }else{
            val adapter = SentRecyclerAdapter(data)
            recycler.adapter = adapter
            recycler.layoutManager = LinearLayoutManager(context)
        }

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
        fun newInstance(param1: Int, param2: String) =
            RequestFragment().apply {
                arguments = Bundle().apply {
                    putInt(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}