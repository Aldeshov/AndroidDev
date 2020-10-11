package com.example.todo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class ToDoListFragment: Fragment() {

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