package com.example.androidfa23


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.androidfa23.Data.OrgAffiliations

class OrgAffiliationsAdapter (private var dataset: List<OrgAffiliations>): RecyclerView.Adapter<OrgAffiliationsAdapter.ViewHolder>()   {
    class ViewHolder(view: View): RecyclerView.ViewHolder(view){
        val affiliation: TextView
        val add : TextView
        init{
            affiliation = view.findViewById(R.id.affiliationTV)
            add = view.findViewById(R.id.addTV)
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): OrgAffiliationsAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.affiliation_item, parent, false)

        return OrgAffiliationsAdapter.ViewHolder(view)
    }

    override fun getItemCount(): Int = dataset.size

    override fun onBindViewHolder(holder: OrgAffiliationsAdapter.ViewHolder, position: Int) {
        holder.affiliation.text = dataset[position].affiliation
        holder.add.setOnClickListener {} //TODO
    }
}