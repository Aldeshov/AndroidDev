package com.example.post

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.post.api.APIService
import com.example.post.fragment.PostListFragmentDirections
import com.example.post.model.Post
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PostListAdapter(var items: ArrayList<Post>, var context: Context?): RecyclerView.Adapter<PostListAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.post_short, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item: Post = items[position]
        holder.title.text = item.title
        holder.id = item.id
    }

    override fun getItemCount(): Int {
        return items.size
    }

    class ViewHolder(view: View): RecyclerView.ViewHolder(view) {
        var id: Int = -1
        var content: ConstraintLayout = view.findViewById(R.id.content)
        var title: TextView = view.findViewById(R.id.name)
        companion object {
            val singleton = Service()
        }

        init {
            content.setOnClickListener {
                (singleton.service as APIService).getPost(id).enqueue(object : Callback<Post> {
                    override fun onResponse(call: Call<Post>, response: Response<Post>) {
                        val action =
                            PostListFragmentDirections.actionPostListFragmentToPostFragment3(
                                response.body()!!
                            )
                        it.findNavController().navigate(action)
                    }

                    override fun onFailure(call: Call<Post>, t: Throwable) {
                        Log.e("Connection Error", t.message!!)
                    }
                })
            }
        }
    }
}