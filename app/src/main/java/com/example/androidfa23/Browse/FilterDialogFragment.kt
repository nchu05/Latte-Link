package com.example.androidfa23.Browse

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.DialogFragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.androidfa23.Data.FilterClass
import com.example.androidfa23.R

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [FilterDialogFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class FilterDialogFragment : DialogFragment() {
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
        val view : View = inflater.inflate(R.layout.fragment_filter_dialog, container, false)
        val X : TextView = view.findViewById(R.id.closeButton)
        val recycler : RecyclerView = view.findViewById(R.id.recyclerView)
        val data = listOf(
            FilterClass("Art"),
            FilterClass("Consulting"),
            FilterClass("Computer Science"),
            FilterClass("Cultural"),
            FilterClass("Design"),
        )
        recycler.adapter = FilterRecyclerAdapter(data)
        recycler.layoutManager = LinearLayoutManager(context)
        val saveButton : ImageView = view.findViewById(R.id.saveButton)
        saveButton.setOnClickListener {
            dismiss()
        }
        X.setOnClickListener {
            dismiss()
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
         * @return A new instance of fragment FilterDialog.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            FilterDialogFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}