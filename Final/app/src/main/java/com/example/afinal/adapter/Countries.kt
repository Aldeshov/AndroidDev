package com.example.afinal.adapter

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.afinal.R
import com.example.afinal.models.Country

class Countries(var items: List<Country>, var context: Context?): RecyclerView.Adapter<Countries.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.recycler_item, parent, false)
        return ViewHolder(items, view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item: Country = items[position]
        holder.name.text = item.name
        holder.id = item.id!!
    }

    override fun getItemCount(): Int {
        return items.size
    }

    class ViewHolder(var items: List<Country>, view: View): RecyclerView.ViewHolder(view) {
        var id: Int = -1
        var content: ConstraintLayout = view.findViewById(R.id.content)
        var name: TextView = view.findViewById(R.id.name)

        init {
            content.setOnClickListener {
                val bundle = Bundle()
                bundle.putParcelable("country", items[id - 1])  // Key, value
                it.findNavController().navigate(R.id.action_countries_to_categories, bundle)
            }
        }
    }
}