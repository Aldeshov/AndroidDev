package com.example.post

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import com.example.post.api.APIService
import com.example.post.model.Post
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    companion object {
        val singleton = Service()
    }
    private var items =  ArrayList<Post>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        (singleton.service as APIService).getPosts().enqueue(object : Callback<ArrayList<Post>> {
            override fun onResponse(call: Call<ArrayList<Post>>, response: Response<ArrayList<Post>>) {
                items.addAll(response.body()!!)
                val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
                val bundle = Bundle()
                bundle.putParcelableArrayList("post_list", items)  // Key, value

                navHostFragment.navController.setGraph(R.navigation.nav_graph, bundle)
                navHostFragment.navController.navigateUp()
            }

            override fun onFailure(call: Call<ArrayList<Post>>, t: Throwable) {
                Log.e("Connection Error", t.message!!)
            }
        })
    }
}