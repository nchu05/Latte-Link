package com.example.androidfa23


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.androidfa23.Data.RequestClass

class SentRecyclerAdapter (private var dataset: List<RequestClass>): RecyclerView.Adapter<SentRecyclerAdapter.ViewHolder>()   {
    class ViewHolder(view: View): RecyclerView.ViewHolder(view){
        val title: TextView
        val person: TextView
        val location: TextView
        val messageButton: ImageView
        val message: TextView
        val card: CardView
        val status: ImageView
        init{
            title = view.findViewById(R.id.title)
            person = view.findViewById(R.id.person)
            location = view.findViewById(R.id.location)
            messageButton = view.findViewById(R.id.dropdown)
            message = view.findViewById(R.id.message)
            card = view.findViewById(R.id.card)
            status = view.findViewById(R.id.status)
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
        if (request.accepted==true){
            holder.card.setBackgroundColor(ContextCompat.getColor(holder.card.context, R.color.beige))
            holder.status.setImageResource(R.drawable.request_accepted)

        }else if (request.accepted==false){
            holder.card.setBackgroundColor(ContextCompat.getColor(holder.card.context, R.color.beige))
            holder.status.setImageResource(R.drawable.request_denied)

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