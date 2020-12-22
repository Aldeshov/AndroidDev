package com.example.post.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.post.CommentListAdapter
import com.example.post.PostListAdapter
import com.example.post.R
import com.example.post.model.Comment

class CommentListFragment: Fragment() {
    private var items =  ArrayList<Comment>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val bundle = this.arguments
        if (bundle != null) {
            items = bundle.getParcelableArrayList("comment_list")!! // Key
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val layout = inflater.inflate(R.layout.comment_list, container, false)
        val list = layout.findViewById(R.id.comment_list_view) as RecyclerView?
        val adapter = CommentListAdapter(items, activity)
        val layoutManager = LinearLayoutManager(activity)
        list?.layoutManager = layoutManager
        list?.adapter = adapter
        return layout
    }
}