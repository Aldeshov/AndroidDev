package com.example.midterm

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.midterm.model.ToDoItem

class ToDoListFragment: Fragment() {
    private lateinit var items: ArrayList<ToDoItem>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val bundle = this.arguments
        if (bundle != null) {
            items = bundle.getParcelableArrayList("data")!! // Key
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val layout = inflater.inflate(R.layout.todo_list, container, false)
        val list = layout.findViewById(R.id.list_view) as RecyclerView?
        val adapter = ToDoListAdapter(items, activity)
        val layoutManager = LinearLayoutManager(activity)
        list?.layoutManager = layoutManager
        list?.adapter = adapter

        return layout
    }
}