package com.example.androidfa23


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.androidfa23.Data.OrgAffiliations

class AvailabilitiesAdapter (private var dataset: List<String>): RecyclerView.Adapter<AvailabilitiesAdapter.ViewHolder>()   {
    class ViewHolder(view: View): RecyclerView.ViewHolder(view){
        val time: Button
        init{
            time = view.findViewById(R.id.time)
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): AvailabilitiesAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.availabilities_item, parent, false)

        return AvailabilitiesAdapter.ViewHolder(view)
    }

    override fun getItemCount(): Int = dataset.size

    override fun onBindViewHolder(holder: AvailabilitiesAdapter.ViewHolder, position: Int) {
        holder.time.text = dataset[position]
    }
}