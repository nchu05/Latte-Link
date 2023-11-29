package com.example.androidfa23


import android.content.res.ColorStateList
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.example.androidfa23.Data.OrgAffiliations

class AvailabilitiesAdapter (private var dataset: List<String>): RecyclerView.Adapter<AvailabilitiesAdapter.ViewHolder>()   {
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
    ): AvailabilitiesAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.availabilities_item, parent, false)

        return AvailabilitiesAdapter.ViewHolder(view)
    }

    override fun getItemCount(): Int = dataset.size

    override fun onBindViewHolder(holder: AvailabilitiesAdapter.ViewHolder, position: Int) {
        holder.time.text = dataset[position]
        holder.X.setOnClickListener {
            holder.X.isVisible = false
            holder.time.backgroundTintList = ContextCompat.getColorStateList(holder.time.context, R.color.white)
        }
        holder.time.setOnClickListener {
            holder.X.isVisible = true
            holder.time.backgroundTintList = ColorStateList.valueOf(holder.time.context.resources.getColor(R.color.beige))
        }
    }
}