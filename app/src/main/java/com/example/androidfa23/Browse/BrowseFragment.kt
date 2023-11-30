package com.example.androidfa23.Browse

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageView
import androidx.fragment.app.Fragment
import com.example.androidfa23.Onboarding.AddInvolvementsDialogFragment
import com.example.androidfa23.R
import com.google.android.material.tabs.TabLayout

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"
/**
 * A simple [Fragment] subclass.
 * Use the [BrowseFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class BrowseFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    // TODO: change into List
    //private var data = ArrayList<OrganizationClass>()

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
        val view =  inflater.inflate(R.layout.fragment_browse, container, false)
        val tabLayout : TabLayout = view.findViewById(R.id.tabLayout)
        val search : EditText = view.findViewById(R.id.searchOrgET)
        val filterIcon : ImageView = view.findViewById(R.id.filterIcon)
        val searchIcon : ImageView = view.findViewById(R.id.searchIcon)
        val browseOrganizationFragment : BrowseOrganizationFragment = BrowseOrganizationFragment.newInstance("", "")
        val browsePeopleFragment : BrowsePeopleFragment = BrowsePeopleFragment.newInstance("", "")

        filterIcon.setOnClickListener {
            val newFragment = FilterDialogFragment()
            newFragment.show(childFragmentManager, "Filter")
        }

        parentFragmentManager.beginTransaction()
            .replace(
                R.id.fragContainerView,
                browseOrganizationFragment
            )
            .commit()
        tabLayout.addOnTabSelectedListener(object: TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                if (tab != null) {
                    when (tab.position) {
                        0 -> {
                            searchIcon.setOnClickListener {
                                browseOrganizationFragment.filterWithQuery(search.text.toString())
                            }
                            parentFragmentManager.beginTransaction()
                                .replace(
                                    R.id.fragContainerView,
                                    browseOrganizationFragment
                                )
                                .commit()
                        }
                        1 -> {
                            parentFragmentManager.beginTransaction()
                                .replace(
                                    R.id.fragContainerView,
                                    browsePeopleFragment
                                )
                                .commit()
                        }
                    }
                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {
            }
        })
        tabLayout.selectTab(tabLayout.getTabAt(0))

        return view
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment BrowseClubsFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            BrowseFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}