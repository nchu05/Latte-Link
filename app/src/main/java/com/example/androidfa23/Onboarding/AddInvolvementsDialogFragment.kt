package com.example.androidfa23.Onboarding

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.DialogFragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.androidfa23.Data.OrgAffiliations
import com.example.androidfa23.OrgAffiliationsAdapter
import com.example.androidfa23.R

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [AddInvolvementsDialogFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class AddInvolvementsDialogFragment : DialogFragment() {
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
        val view : View = inflater.inflate(R.layout.fragment_add_involvements, container, false)
        val addButton : ImageView = view.findViewById(R.id.addButton)
        val closeButton : TextView = view.findViewById(R.id.closeButton)
        val recycler : RecyclerView = view.findViewById(R.id.recyclerView)
        val data = listOf(
            OrgAffiliations(0, "Project Team"),
            OrgAffiliations(1, "Design"),
            OrgAffiliations(2, "Dev"),
        )
        val adapter = OrgAffiliationsAdapter(data)
        recycler.adapter = adapter
        recycler.layoutManager = LinearLayoutManager(context)

        closeButton.setOnClickListener {
            dismiss()
        }
        addButton.setOnClickListener {
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
         * @return A new instance of fragment add_involvements.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            AddInvolvementsDialogFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}