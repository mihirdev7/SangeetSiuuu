package com.example.sangeet

import android.content.Context
import android.Manifest
import android.content.pm.PackageManager
import android.health.connect.datatypes.units.Length
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.widget.Adapter
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.io.File

class MainActivity2 : AppCompatActivity() {
    private val PERMISSION_REQUEST_CODE=123
    private lateinit var recyycler : RecyclerView
    private lateinit var adapter : MyAdapter2

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        recyycler=findViewById(R.id.recyycler)
        recyycler.layoutManager=LinearLayoutManager(this)

        if(checkPermission()){
            LoadSongs()
        }
    }
    private fun checkPermission(): Boolean{
        if(ContextCompat.checkSelfPermission(this,Manifest.permission.READ_EXTERNAL_STORAGE)!=PackageManager.PERMISSION_GRANTED){
                ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE),PERMISSION_REQUEST_CODE)
            return false
        }
        return true
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if(requestCode==PERMISSION_REQUEST_CODE){
            if(grantResults.isNotEmpty() && grantResults[0]==PackageManager.PERMISSION_GRANTED){
                    LoadSongs()
            }else{
                Toast.makeText(this,"Denied",Toast.LENGTH_LONG).show()
            }
        }
    }
    private  fun LoadSongs(){
        val musicDirectory=File(Environment.getExternalStorageDirectory().toString())
        val songs=getALLMP3Files(musicDirectory)
        adapter= MyAdapter2(this,songs)
        recyycler.adapter=adapter
    }
    private fun getALLMP3Files(direstory:File): List<File> {
        val mp3Files = mutableListOf<File>()
        val files=direstory.listFiles()
        if(files !=null){
            for(file in files){
                if(file.isDirectory){
                    mp3Files.addAll(getALLMP3Files(file))
                }else if(file.isFile && file.extension.equals("mp3",ignoreCase = true)){
                    mp3Files.add(file)
                }
            }
        }
        return mp3Files
    }

}