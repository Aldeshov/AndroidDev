package com.example.afinal.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.afinal.Database
import com.example.afinal.MyApplication
import com.example.afinal.R
import com.example.afinal.adapter.Categories
import com.example.afinal.dao.CategoryDao
import com.example.afinal.models.Country

class Categories: Fragment() {
    lateinit var db: Database
    lateinit var categoryDao: CategoryDao
    private lateinit var items: List<com.example.afinal.models.Category>
    private lateinit var item: Country
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        db = MyApplication.instance.getDatabase()!!
        categoryDao = db.categoryDao()
        val bundle = this.arguments
        if (bundle != null) {
            item = bundle.getParcelable("country")!!
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        items = categoryDao.getAll()

        val layout = inflater.inflate(R.layout.recycler, container, false)
        val list = layout.findViewById(R.id.list_view) as RecyclerView?
        val adapter = Categories(items, item, activity)
        val layoutManager = LinearLayoutManager(activity)
        list?.layoutManager = layoutManager
        list?.adapter = adapter

        return layout
    }
}