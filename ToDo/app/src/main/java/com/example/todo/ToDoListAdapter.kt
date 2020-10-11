package com.example.todo

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView

class ToDoListAdapter(var items: ArrayList<ToDoItem>, var context: Context?): RecyclerView.Adapter<ToDoListAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.todo_item_short, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item: ToDoItem = items[position]
        holder.header.text = item.head
        holder.shortbody.text = item.short_body
        holder.isDone.isChecked = item.is_done
        holder.id = item.id
    }

    override fun getItemCount(): Int {
        return items.size
    }

    class ViewHolder(view: View): RecyclerView.ViewHolder(view), View.OnClickListener {
        lateinit var id: String
        var content: ConstraintLayout = view.findViewById(R.id.content)
        var header: TextView = view.findViewById(R.id.header)
        var shortbody: TextView = view.findViewById(R.id.short_body)
        var isDone: CheckBox = view.findViewById(R.id.is_done)

        init {
            content.setOnClickListener(this);
            isDone.setOnClickListener {
                items.find { i -> i.id == this.id }?.is_done = isDone.isChecked
            }
        }

        override fun onClick(v: View?) {
            val activity = v?.context
            val item = items.find { i -> i.id == this.id }
            (activity as AppCompatActivity).supportFragmentManager.beginTransaction()
                .replace(R.id.main, ToDoItemFragment(item))
                .addToBackStack("secondary")
                .commit();
        }
    }
}