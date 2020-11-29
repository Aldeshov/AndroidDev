package com.example.todo.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.todo.R
import com.example.todo.ToDoListAdapter
import com.example.todo.model.ToDo
import kotlinx.android.synthetic.main.todo_list.*

class ToDoListFragment: Fragment() {
    private var items =  ArrayList<ToDo>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val bundle = this.arguments
        if (bundle != null) {
            items = bundle.getParcelableArrayList("todo_list")!! // Key
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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        add_item.setOnClickListener {
            it.findNavController().navigate(R.id.action_toDoListFragment_to_toDoAddFragment)
        }
    }
}