package com.example.todo.api

import com.example.todo.model.ToDo
import retrofit2.Call
import retrofit2.http.*


interface APIService {
    @GET("todos/")
    fun getAll(): Call<ArrayList<ToDo>>

    @GET("todos/{id}")
    fun getToDo(@Path("id") todoInt: Int): Call<ToDo>

    @FormUrlEncoded
    @POST("todos/")
    fun addTodo(
        @Field("userId") userId: Int,
        @Field("title") title: String?,
        @Field("completed") completed: Boolean
    ): Call<ToDo>
}