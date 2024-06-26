package com.example.proiectandroid.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.proiectandroid.R
import com.example.proiectandroid.models.Coffee

class CoffeesAdapter(private var coffeesList: MutableList<Coffee>, private val itemClickListener: (Coffee) -> Unit) : RecyclerView.Adapter<CoffeesAdapter.CoffeesViewHolder>() {

    inner class CoffeesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val nameTextView: TextView = itemView.findViewById(R.id.nameTextView)

        init {
            itemView.setOnClickListener {
                itemClickListener(coffeesList[adapterPosition])
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CoffeesViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.coffee_item, parent, false)
        return CoffeesViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: CoffeesViewHolder, position: Int) {
        val coffee = coffeesList[position]
        holder.nameTextView.text = coffee.name
    }

    override fun getItemCount() = coffeesList.size

    fun updateData(newCoffeesList: List<Coffee>) {
        Log.d("CoffeesAdapter", "Updating data: $newCoffeesList")
        coffeesList.clear()
        coffeesList.addAll(newCoffeesList)
        notifyDataSetChanged()
    }
}