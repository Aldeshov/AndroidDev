package com.example.post.fragment

import android.os.Bundle
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.example.post.R
import com.example.post.Service
import com.example.post.api.APIService
import com.example.post.model.*
import kotlinx.android.synthetic.main.post.*

class PostFragment(): Fragment() {
    private lateinit var item: Post
    companion object {
        val singleton = Service()
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val bundle = this.arguments
        if (bundle != null) {
            item = bundle.getParcelable("post")!!
        }
    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val layout = inflater.inflate(R.layout.post, container, false)
        (layout.findViewById(R.id.id) as TextView).text = item.id.toString()
        (layout.findViewById(R.id.body) as TextView).text = item.body.toString()
        (layout.findViewById(R.id.user_id) as TextView).text = item.userId.toString()
        (layout.findViewById(R.id.title) as TextView).text = item.title.toString()
        return layout
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        go_comment.setOnClickListener {
            (singleton.service as APIService).getComments(item.id).enqueue(object : Callback<ArrayList<Comment>> {
                override fun onResponse(call: Call<ArrayList<Comment>>, response: Response<ArrayList<Comment>>) {
                    val bundle = Bundle()
                    bundle.putParcelableArrayList("comment_list", response.body()!!)  // Key, value
                    it.findNavController().navigate(R.id.action_postFragment_to_commentListFragment, bundle)
                }

                override fun onFailure(call: Call<ArrayList<Comment>>, t: Throwable) {
                    Log.e("Connection Error", t.message!!)
                }
            })
        }
    }
}