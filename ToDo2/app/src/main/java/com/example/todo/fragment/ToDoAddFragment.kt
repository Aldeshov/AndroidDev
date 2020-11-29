package com.example.todo.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.example.todo.R
import com.example.todo.Service
import com.example.todo.api.APIService
import com.example.todo.model.ToDo
import kotlinx.android.synthetic.main.todo_add_item.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ToDoAddFragment: Fragment() {
    private val list = ArrayList<ToDo>()
    companion object {
        val singleton = Service()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (singleton.service as APIService).getAll().enqueue(object : Callback<ArrayList<ToDo>> {
            override fun onResponse(call: Call<ArrayList<ToDo>>, response: Response<ArrayList<ToDo>>) {
                list.addAll(response.body()!!)
            }

            override fun onFailure(call: Call<ArrayList<ToDo>>, t: Throwable) {
                Log.e("Connection Error", t.message!!)
            }
        })
    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.todo_add_item, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val title = add_title.text.toString()
        val userId = (0..11).random()

        (singleton.service as APIService).addTodo(userId, title, false).enqueue(object :
            Callback<ToDo> {
            override fun onResponse(call: Call<ToDo>, response: Response<ToDo>) {
                Log.e("OK", response.body().toString())
            }

            override fun onFailure(call: Call<ToDo>, t: Throwable) {
                Log.e("Connection Error", t.message!!)
            }
        })

        list.add(ToDo(false, (200..1000).random(), title, userId))

        add_item.setOnClickListener {
            it.findNavController().navigate(R.id.action_toDoAddFragment_to_toDoListFragment)
        }
    }
}