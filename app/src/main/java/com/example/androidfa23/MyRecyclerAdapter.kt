package com.example.androidfa23

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView

class MyRecyclerAdapter(private var dataset: List<Organization>): RecyclerView.Adapter<MyRecyclerAdapter.ViewHolder>() {
    class ViewHolder(view: View): RecyclerView.ViewHolder(view){
        val orgName: TextView
        val card: CardView
        init{
            orgName = view.findViewById(R.id.orgName)
            card = view.findViewById(R.id.card)
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.org_item, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = dataset.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val org = dataset[position]
        holder.orgName.text = org.name
        holder.card.setOnClickListener{
            //open org_activity
            // TODO: Make org_activity, should have a recyclerview with all persons involved and other org details

        }
    }
}