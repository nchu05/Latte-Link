package com.example.androidfa23


import android.content.res.ColorStateList
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.RecyclerView
import com.example.androidfa23.Data.OrgAffiliations
import com.example.androidfa23.Onboarding.AvailabilitiesDialogFragment
import com.example.androidfa23.Profile.AlmostLinkedDialogFragment

class ProfileTimesAdapter (private var dataset: List<String>, var fragmentManager : FragmentManager): RecyclerView.Adapter<ProfileTimesAdapter.ViewHolder>()   {
    class ViewHolder(view: View): RecyclerView.ViewHolder(view){
        val time: Button
        val X : TextView
        init{
            time = view.findViewById(R.id.time)
            X = view.findViewById(R.id.X)
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ProfileTimesAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.availabilities_item, parent, false)

        return ProfileTimesAdapter.ViewHolder(view)
    }

    override fun getItemCount(): Int = dataset.size

    override fun onBindViewHolder(holder: ProfileTimesAdapter.ViewHolder, position: Int) {
        holder.time.setOnClickListener {
            val newFragment = AlmostLinkedDialogFragment.newInstance(holder.time.text.toString(),"")
            newFragment.show(fragmentManager, "Availabilities")
        }
        holder.time.text = dataset[position]
        holder.time.backgroundTintList = ContextCompat.getColorStateList(holder.time.context, R.color.beige)
    }
}