package com.example.midterm

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.midterm.model.*

class ToDoItemFragment(private var item: ToDoItem?): Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val layout = inflater.inflate(R.layout.todo_item, container, false)
        (layout.findViewById(R.id.id) as TextView).text = item?.id ?: "null"
        (layout.findViewById(R.id.status) as TextView).text = "Status: " + item?.status.toString()
        (layout.findViewById(R.id.title) as TextView).text = item?.title ?: "null"
        (layout.findViewById(R.id.category) as TextView).text = item?.category ?: "null"
        (layout.findViewById(R.id.duration) as TextView).text = item?.duration ?: "null"
        (layout.findViewById(R.id.description) as TextView).text = item?.description ?: "null"
        return layout
    }
}