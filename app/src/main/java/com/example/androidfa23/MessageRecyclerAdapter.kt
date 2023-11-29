package com.example.androidfa23

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.androidfa23.Data.RequestClass

class MessageRecyclerAdapter (private var dataset: List<RequestClass>): RecyclerView.Adapter<MessageRecyclerAdapter.ViewHolder>()   {
    class ViewHolder(view: View): RecyclerView.ViewHolder(view){
        val title: TextView
        val person: TextView
        val location: TextView
        init{
            title = view.findViewById(R.id.title)
            person = view.findViewById(R.id.person)
            location = view.findViewById(R.id.location)
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MessageRecyclerAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.received_item, parent, false)

        return MessageRecyclerAdapter.ViewHolder(view)
    }

    override fun getItemCount(): Int = dataset.size

    override fun onBindViewHolder(holder: MessageRecyclerAdapter.ViewHolder, position: Int) {
        val request = dataset[position]
        holder.title.text = "Request for ${request.dateTime}"
        holder.location.text = request.location
        holder.person.text = "From ${request.requester.name}"

    }
}