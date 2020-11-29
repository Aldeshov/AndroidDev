package com.example.todo

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import com.example.todo.api.APIService
import com.example.todo.fragment.ToDoListFragment
import com.example.todo.model.ToDo
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    companion object {
        val singleton = Service()
    }
    private var items =  ArrayList<ToDo>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        (singleton.service as APIService).getAll().enqueue(object : Callback<ArrayList<ToDo>> {
            override fun onResponse(call: Call<ArrayList<ToDo>>, response: Response<ArrayList<ToDo>>) {
                items.addAll(response.body()!!)
                val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
                val bundle = Bundle()
                bundle.putParcelableArrayList("todo_list", items)  // Key, value

                navHostFragment.navController.setGraph(R.navigation.nav_graph, bundle)
                navHostFragment.navController.navigateUp()
            }

            override fun onFailure(call: Call<ArrayList<ToDo>>, t: Throwable) {
                Log.e("Connection Error", t.message!!)
            }
        })
    }
}