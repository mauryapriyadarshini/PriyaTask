package com.example.tarams.network

import com.example.tarams.model.NewsResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface URLInterface {
    @GET ("top-headlines")
    fun getHeadlines(@Query("country") country: String,
                     @Query("page") page: Int,
                     @Query("pageSize") pageSize: Int
    ): Call<NewsResponse>
}