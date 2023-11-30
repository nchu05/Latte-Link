package com.example.androidfa23

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.androidfa23.Data.RequestClass

class RequestGroupRecyclerAdapter(private var dataset: List<Pair<String, List<RequestClass>>>): RecyclerView.Adapter<RequestGroupRecyclerAdapter.ViewHolder>() {

    class ViewHolder(view: View): RecyclerView.ViewHolder(view){
        val date: TextView
        val events: RecyclerView
        init{
            date = view.findViewById(R.id.date)
            events = view.findViewById(R.id.recyclerView)
        }
    }
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RequestGroupRecyclerAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.event_group_item, parent, false)

        return RequestGroupRecyclerAdapter.ViewHolder(view)
    }

    override fun getItemCount(): Int = dataset.size

    override fun onBindViewHolder(holder: RequestGroupRecyclerAdapter.ViewHolder, position: Int) {
        val group = dataset[position]
        holder.date.text = dataset[position].first
        val adapter = RequestsRecyclerAdapter(dataset[position].second)
        holder.events.adapter = adapter
        holder.events.layoutManager = LinearLayoutManager(holder.date.context)

    }
}