package com.example.todo.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import com.example.todo.MainActivity
import com.example.todo.R
import com.example.todo.Service
import com.example.todo.api.APIService
import com.example.todo.model.*
import kotlinx.android.synthetic.main.todo_item.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ToDoItemFragment(): Fragment() {
    private lateinit var item: ToDo
    companion object {
        val singleton = Service()
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val bundle = this.arguments
        if (bundle != null) {
            item = bundle.getParcelable("todo")!!
        }
    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val layout = inflater.inflate(R.layout.todo_item, container, false)
        (layout.findViewById(R.id.id) as TextView).text = item.id.toString()
        (layout.findViewById(R.id.short_completed) as TextView).text = if(item.completed) "Done" else "In Process"
        (layout.findViewById(R.id.userId) as TextView).text = item.userId.toString()
        (layout.findViewById(R.id.title) as TextView).text = item.title ?: "null"
        return layout
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        back.setOnClickListener {
            (MainActivity.singleton.service as APIService).getAll().enqueue(object : Callback<ArrayList<ToDo>> {
                override fun onResponse(call: Call<ArrayList<ToDo>>, response: Response<ArrayList<ToDo>>) {
                    val bundle = Bundle()
                    bundle.putParcelableArrayList("todo_list", response.body()!!)  // Key, value
                    it.findNavController().navigate(R.id.action_toDoItemFragment_to_toDoListFragment, bundle)

                }

                override fun onFailure(call: Call<ArrayList<ToDo>>, t: Throwable) {
                    Log.e("Connection Error", t.message!!)
                }
            })
        }
    }
}