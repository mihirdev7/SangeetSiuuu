package com.example.sangeet

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface ApiInterface {
    @Headers("//your api key")
    @GET("search")
    fun getData(@Query("q") query: String): Call<MyData>
}