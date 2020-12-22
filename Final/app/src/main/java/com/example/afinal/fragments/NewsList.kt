package com.example.afinal.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.afinal.R
import com.example.afinal.adapter.NewsList
import com.example.afinal.models.News

class NewsList: Fragment() {
    lateinit var  item: News

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val bundle = this.arguments
        if (bundle != null) {
            item = bundle.getParcelable("news")!! // Key
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val layout = inflater.inflate(R.layout.recycler, container, false)
        val list = layout.findViewById(R.id.list_view) as RecyclerView?
        val adapter = NewsList(item, activity)
        val layoutManager = LinearLayoutManager(activity)
        list?.layoutManager = layoutManager
        list?.adapter = adapter

        return layout
    }
}