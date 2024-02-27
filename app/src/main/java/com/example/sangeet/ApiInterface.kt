package com.example.sangeet

import com.example.sangeet.Models.MyData
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface ApiInterface {
    @Headers("headers")
    @GET("search")
    fun getData(@Query("q") query: String): Call<MyData>

}