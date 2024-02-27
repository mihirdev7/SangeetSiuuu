package com.example.sangeet

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.sangeet.Adapters.ApiMusicAdapter
import com.example.sangeet.Models.MyData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiMusicFetchActivity : AppCompatActivity() {
        lateinit var recyclr:RecyclerView
        lateinit var myAdapter: ApiMusicAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        recyclr=findViewById(R.id.recy)
        val searchky=intent?.getStringExtra("searchkey").toString()

        val retrofitBuilder=Retrofit.Builder()
            .baseUrl("https://deezerdevs-deezer.p.rapidapi.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiInterface::class.java)
        val retrofitData=retrofitBuilder.getData(searchky)
        retrofitData.enqueue(object : Callback<MyData?>{
            override fun onResponse(call: Call<MyData?>, response: Response<MyData?>) {
                response.isSuccessful
                    val dataList=response.body()?.data!!
                    if(dataList.isNotEmpty()){
                        myAdapter= ApiMusicAdapter(this@ApiMusicFetchActivity,dataList)
                        recyclr.adapter=myAdapter
                        recyclr.layoutManager=LinearLayoutManager(this@ApiMusicFetchActivity)
                    }else{
                        Toast.makeText(this@ApiMusicFetchActivity,"Not found",Toast.LENGTH_SHORT).show()
                        finish() }

            }

            override fun onFailure(call: Call<MyData?>, t: Throwable) {
            Toast.makeText(this@ApiMusicFetchActivity,"Internet Required",Toast.LENGTH_LONG).show()
             }
        })
    }

}
