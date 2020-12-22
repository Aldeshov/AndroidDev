package com.example.afinal.api

import com.example.afinal.models.News
import retrofit2.Call
import retrofit2.http.*

interface APIService {
    @GET("top-headlines/category/{category}/{country_code}.json")
    fun get(@Path("category") category: String?,
            @Path("country_code") country_code: String?): Call<News>
}