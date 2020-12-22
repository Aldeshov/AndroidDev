package com.example.post

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.post.model.Comment

class CommentListAdapter(var items: ArrayList<Comment>, var context: Context?): RecyclerView.Adapter<CommentListAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.comment_short, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item: Comment = items[position]
        holder.name.text = item.name
        holder.body.text = item.body
        holder.id = item.id
    }

    override fun getItemCount(): Int {
        return items.size
    }

    class ViewHolder(view: View): RecyclerView.ViewHolder(view) {
        var id: Int = -1
        var name: TextView = view.findViewById(R.id.name)
        var body: TextView = view.findViewById(R.id.body)
    }
}