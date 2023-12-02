package com.example.androidfa23.Browse

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.example.androidfa23.Data.PersonClass
import com.example.androidfa23.R

class PeopleRecyclerAdapter (private var dataset: List<PersonClass>): RecyclerView.Adapter<PeopleRecyclerAdapter.ViewHolder>() {
    class ViewHolder(view: View): RecyclerView.ViewHolder(view){
        val personName: TextView
        val cardView: CardView
        val image: ImageView
        init{
            personName = view.findViewById(R.id.personName)
            cardView = view.findViewById(R.id.card)
            image = view.findViewById(R.id.profilePic)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.person_item, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = dataset.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val person = dataset[position]
        holder.personName.text = person.name
        holder.cardView.setOnClickListener{
            val intent = Intent(holder.itemView.context, IndividualPersonActivity::class.java)

            intent.putExtra("id", person.id.toString())
            holder.itemView.context.startActivity(intent)

        }
    }
}