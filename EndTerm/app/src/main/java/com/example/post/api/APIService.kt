package com.example.post.api

import com.example.post.model.Comment
import com.example.post.model.Post
import retrofit2.Call
import retrofit2.http.*


interface APIService {
    @GET("posts/")
    fun getPosts(): Call<ArrayList<Post>>

    @GET("posts/{id}")
    fun getPost(@Path("id") todoInt: Int): Call<Post>

    @Headers(*["Cache-Control: max-age=640000", "User-Agent: My-App-Name"])
    @GET("comments")
    fun getComments(
        @Query("postId") postID: Int
    ): Call<ArrayList<Comment>>
}