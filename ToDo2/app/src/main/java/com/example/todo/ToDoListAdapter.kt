package com.example.todo

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.todo.api.APIService
import com.example.todo.fragment.ToDoListFragmentDirections
import com.example.todo.model.ToDo
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ToDoListAdapter(var items: ArrayList<ToDo>, var context: Context?): RecyclerView.Adapter<ToDoListAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.todo_item_short, parent, false)

        return ViewHolder(items, view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item: ToDo = items[position]
        holder.title.text = item.title
        holder.completed.isChecked = item.completed
        holder.id = item.id
    }

    override fun getItemCount(): Int {
        return items.size
    }

    class ViewHolder(var items: ArrayList<ToDo>, view: View): RecyclerView.ViewHolder(view) {
        var id: Int = -1
        var content: ConstraintLayout = view.findViewById(R.id.content)
        var title: TextView = view.findViewById(R.id.short_title)
        var completed: CheckBox = view.findViewById(R.id.short_completed)
        companion object {
            val singleton = Service()
        }

        init {
            content.setOnClickListener {
                (singleton.service as APIService).getToDo(id).enqueue(object : Callback<ToDo> {
                    override fun onResponse(call: Call<ToDo>, response: Response<ToDo>) {
                        val action =
                            ToDoListFragmentDirections.actionToDoListFragmentToToDoItemFragment(
                                response.body()!!
                            )
                        it.findNavController().navigate(action)
                    }

                    override fun onFailure(call: Call<ToDo>, t: Throwable) {
                        Log.e("Connection Error", t.message!!)
                    }
                })
            }

            completed.setOnClickListener {
                val checked = items.find { i -> i.id == this.id }
                if (checked != null) {
                    checked.completed = completed.isChecked
                }
            }
        }
    }
}