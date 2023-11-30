package com.example.androidfa23

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.androidfa23.Data.RequestClass

class ReceivedRecyclerAdapter (private var dataset: List<RequestClass>): RecyclerView.Adapter<ReceivedRecyclerAdapter.ViewHolder>()   {
    class ViewHolder(view: View): RecyclerView.ViewHolder(view){
        val title: TextView
        val person: TextView
        val location: TextView
        val messageButton: ImageView
        val message: TextView
        val card: CardView
        val yes: ImageView
        val no: ImageView
        init{
            title = view.findViewById(R.id.title)
            person = view.findViewById(R.id.person)
            location = view.findViewById(R.id.location)
            messageButton = view.findViewById(R.id.dropdown)
            message = view.findViewById(R.id.message)
            card = view.findViewById(R.id.card)
            yes = view.findViewById(R.id.yesButton)
            no = view.findViewById(R.id.noButton)
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
        holder.person.text = "From ${request.sender.name}"
        if (request.accepted==true){
            holder.card.setBackgroundColor(ContextCompat.getColor(holder.card.context, R.color.beige))
            holder.yes.setImageResource(R.drawable.y_coffee_chat_selected_green)

        }else if (request.accepted==false){
            holder.card.setBackgroundColor(ContextCompat.getColor(holder.card.context, R.color.beige))
            holder.no.setImageResource(R.drawable.n_coffee_chat_selected)

        }else{
            holder.yes.setOnClickListener {
                holder.card.setBackgroundColor(ContextCompat.getColor(holder.card.context, R.color.beige))
                holder.yes.setImageResource(R.drawable.y_coffee_chat_selected_green)
                request.accepted = true
            }
            holder.no.setOnClickListener {
                holder.card.setBackgroundColor(ContextCompat.getColor(holder.card.context, R.color.beige))
                holder.no.setImageResource(R.drawable.n_coffee_chat_selected)
                request.accepted = false
            }
        }
        holder.messageButton.setOnClickListener{
            if (holder.message.visibility==View.INVISIBLE){
                holder.messageButton.setImageResource(R.drawable.collapse)
                holder.message.text = "\n${request.message}\n"
                holder.message.visibility = View.VISIBLE
            }else{
                holder.messageButton.setImageResource(R.drawable.expand)
                holder.message.text = null
                holder.message.visibility = View.INVISIBLE
            }
        }
    }
}