package com.example.androidfa23

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.androidfa23.Data.RequestClass

class RequestsRecyclerAdapter(private var dataset: List<RequestClass>): RecyclerView.Adapter<RequestsRecyclerAdapter.ViewHolder>()  {

    class ViewHolder(view: View): RecyclerView.ViewHolder(view){
        val title: TextView
        val location: TextView
        init{
            title = view.findViewById(R.id.title)
            location = view.findViewById(R.id.location)
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RequestsRecyclerAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.event_item, parent, false)

        return RequestsRecyclerAdapter.ViewHolder(view)
    }

    override fun onBindViewHolder(holder: RequestsRecyclerAdapter.ViewHolder, position: Int) {
        val request = dataset[position]
        holder.title.text = "${request.sender.name} is coffee chatting ${request.receiver.name}"
        holder.location.text = request.location
    }

    override fun getItemCount(): Int = dataset.size

}