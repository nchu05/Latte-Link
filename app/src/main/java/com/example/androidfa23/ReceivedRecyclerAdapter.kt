package com.example.androidfa23

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.androidfa23.Data.RequestClass

class ReceivedRecyclerAdapter (private var dataset: List<RequestClass>): RecyclerView.Adapter<ReceivedRecyclerAdapter.ViewHolder>()   {
    class ViewHolder(view: View): RecyclerView.ViewHolder(view){
        val title: TextView
        val person: TextView
        val location: TextView
        val messageButton: ImageView
        val message: TextView
        init{
            title = view.findViewById(R.id.title)
            person = view.findViewById(R.id.person)
            location = view.findViewById(R.id.location)
            messageButton = view.findViewById(R.id.dropdown)
            message = view.findViewById(R.id.message)
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ReceivedRecyclerAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.received_item, parent, false)

        return ReceivedRecyclerAdapter.ViewHolder(view)
    }

    override fun getItemCount(): Int = dataset.size

    override fun onBindViewHolder(holder: ReceivedRecyclerAdapter.ViewHolder, position: Int) {
        val request = dataset[position]
        holder.title.text = "Request for ${request.dateTime}"
        holder.location.text = request.location
        holder.person.text = "From ${request.requester.name}"
        holder.messageButton.setOnClickListener{
            if (holder.message.visibility==View.INVISIBLE){
                holder.messageButton.setImageResource(R.drawable.collapse)
                holder.message.text = request.message
                holder.message.visibility = View.VISIBLE
            }else{
                holder.messageButton.setImageResource(R.drawable.expand)
                holder.message.text = null
                holder.message.visibility = View.INVISIBLE
            }
        }
    }
}