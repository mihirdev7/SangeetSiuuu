package com.example.sangeet

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class HomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        var butn2=findViewById<Button>(R.id.button2)
        var butn=findViewById<Button>(R.id.button)

        butn2.setOnClickListener {
            val intent=Intent(this,MainActivity2::class.java)
            startActivity(intent)
        }
        butn.setOnClickListener {
            var intent2=Intent(this,MainActivity::class.java)
            startActivity(intent2)
        }
    }
}