package com.example.sangeet

import android.content.Context
import android.media.MediaPlayer
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.SeekBar
import android.widget.TextView
import androidx.core.net.toUri
import androidx.recyclerview.widget.RecyclerView
import java.io.File


class MyAdapter2 (private val context: Context,private val songs: List<File>) : RecyclerView.Adapter<MyAdapter2.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val View=LayoutInflater.from(context).inflate(R.layout.activity_design,parent,false)
        return ViewHolder(View)
    }

    override fun getItemCount(): Int {
        return songs.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val Song=songs[position]
        holder.textt.text=Song.name

        holder.strt.setOnClickListener {
             var mediaPlayer :MediaPlayer= MediaPlayer.create(context,Song.toUri())
            mediaPlayer.start()

        }
    }

    class ViewHolder(itemView: View):RecyclerView.ViewHolder(itemView) {
        val imagee=itemView.findViewById<ImageView>(R.id.img)
        val textt=itemView.findViewById<TextView>(R.id.titlename)
        val strt=itemView.findViewById<ImageButton>(R.id.start)
        val sekbar=itemView.findViewById<SeekBar>(R.id.seekbr)
    }
}