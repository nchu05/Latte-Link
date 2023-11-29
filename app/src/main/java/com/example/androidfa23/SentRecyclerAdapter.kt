package com.example.androidfa23

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.view.marginBottom
import androidx.recyclerview.widget.RecyclerView
import com.example.androidfa23.Data.RequestClass

class SentRecyclerAdapter (private var dataset: List<RequestClass>): RecyclerView.Adapter<SentRecyclerAdapter.ViewHolder>()   {
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
    ): SentRecyclerAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.sent_item, parent, false)

        return SentRecyclerAdapter.ViewHolder(view)
    }

    override fun getItemCount(): Int = dataset.size

    override fun onBindViewHolder(holder: SentRecyclerAdapter.ViewHolder, position: Int) {
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