package com.example.androidfa23.Onboarding

import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.DialogFragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.androidfa23.AvailabilitiesAdapter
import com.example.androidfa23.Browse.OrgRecyclerAdapter
import com.example.androidfa23.R

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [AvailabilitiesDialogFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class AvailabilitiesDialogFragment(day: String) : DialogFragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = day
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return super.onCreateDialog(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view : View = inflater.inflate(R.layout.fragment_availabilities_dialog_fragment, container, false)
        val availabilitiesTitle : TextView = view.findViewById(R.id.availabilitiesTitle)
        availabilitiesTitle.text = "${this.param1} Availabilities"
        val saveButton : ImageView = view.findViewById(R.id.saveButton)
        val closeButton : TextView = view.findViewById(R.id.closeButton)
        val amPmSwitch : ImageView = view.findViewById(R.id.amPmSwitch)
        val dataAM = listOf(
            "7-7:30 AM",
            "7:30-8 AM",
            "8-8:30 AM",
            "8:30-9 AM",
            "9-9:30 AM",
            "9:30-10 AM",
            "10-10:30 AM",
            "10:30-11 AM",
            "11-11:30 AM",
            "11:30-12 PM",
            )
        val dataPM = listOf(
            "12-12:30 PM",
            "12:30-1 PM",
            "1-1:30 PM",
            "1:30-2 PM",
            "2-2:30 PM",
            "2:30-3 PM",
            "3-3:30 PM",
            "3:30-4 PM",
            "4-4:30 PM",
            "4:30-5 PM",
            "5-5:30 PM",
            "5:30-6 PM",
            "6-6:30 PM",
            "6:30-7 PM",
            "7-7:30 PM",
            "7:30-8 PM",
            "8-8:30 PM",
            "8:30-9 PM"
        )
        val recycler : RecyclerView = view.findViewById(R.id.recyclerView)
        var adapter = AvailabilitiesAdapter(dataAM)
        recycler.adapter = adapter
        recycler.layoutManager = GridLayoutManager(context, 2)
        var am = true
        amPmSwitch.setOnClickListener{
            if (am) {
                amPmSwitch.setImageResource(R.drawable.pm)
                adapter = AvailabilitiesAdapter(dataPM)
                recycler.adapter = adapter
                am = false
            }
            else {
                amPmSwitch.setImageResource(R.drawable.am_png)
                adapter = AvailabilitiesAdapter(dataAM)
                recycler.adapter = adapter
                am = true
            }
        }
        closeButton.setOnClickListener {
            dismiss()
        }
        saveButton.setOnClickListener {
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
         * @return A new instance of fragment availabilities_dialog_fragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            AvailabilitiesDialogFragment("name").apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}