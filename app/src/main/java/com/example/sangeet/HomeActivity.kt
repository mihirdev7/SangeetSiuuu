package com.example.sangeet

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.KeyEvent
import android.widget.Button
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.sangeet.Adapters.ArtistsAdapter
import com.google.android.material.textfield.TextInputEditText

class HomeActivity : AppCompatActivity() {
    lateinit var recyclr: RecyclerView
    lateinit var artistsAdapter: ArtistsAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        recyclr=findViewById(R.id.recyycler)
        val StorageBtn=findViewById<Button>(R.id.StorageBtn)
        val searchText=findViewById<TextInputEditText>(R.id.searchText)

        searchText.setOnKeyListener { view, i, keyEvent ->
            if((keyEvent.action==KeyEvent.ACTION_DOWN) && (i==KeyEvent.KEYCODE_ENTER) && searchText.text!!.isNotEmpty()){
                val text:String=searchText.text.toString()
                val intent3=Intent(this@HomeActivity,ApiMusicFetchActivity::class.java)
                intent3.putExtra("searchkey",text)
                startActivity(intent3)
                return@setOnKeyListener true
            }
            return@setOnKeyListener false
        }

        val datas= listOf<String>("Eminem","Drake","Taylor Swift","Snoop Dog", "Weekend")
        artistsAdapter= ArtistsAdapter(this,datas)
        recyclr.adapter=artistsAdapter
        recyclr.layoutManager=LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false)

        StorageBtn.setOnClickListener {
            val intent=Intent(this,StorageMusicFetchActivity::class.java)
            startActivity(intent)
        }
    }
}