package com.example.todo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment

class ToDoItemFragment(var item: ToDoItem?): Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val layout = inflater.inflate(R.layout.todo_item, container, false)
        (layout.findViewById(R.id.item_header) as TextView).text = item?.head ?: "null"
        (layout.findViewById(R.id.item_short_body) as TextView).text = item?.short_body ?: "null"
        (layout.findViewById(R.id.item_body) as TextView).text = item?.body ?: "null"
        return layout
    }
}