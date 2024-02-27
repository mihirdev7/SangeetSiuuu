package com.example.sangeet.Adapters

import android.app.Activity
import android.app.Dialog
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.core.net.toUri
import androidx.media3.common.MediaItem
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.ui.PlayerView
import androidx.recyclerview.widget.RecyclerView
import com.example.sangeet.Models.Data
import com.example.sangeet.R
import com.google.android.material.imageview.ShapeableImageView
import com.squareup.picasso.Picasso

class ApiMusicAdapter(val context: Activity, val dataList:List<Data>)
    : RecyclerView.Adapter<ApiMusicAdapter.MyViewHolder>(){
    private lateinit var player: ExoPlayer

    init {
        player = ExoPlayer.Builder(context).build()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemVew= LayoutInflater.from(context).inflate(R.layout.activity_design,parent,false)
        return MyViewHolder(itemVew)
    }
    override fun getItemCount(): Int {
        return dataList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        if(dataList.isEmpty()){
            Toast.makeText(context,"Not found", Toast.LENGTH_LONG).show()
            context.finish()
        }
        val currentData=dataList[position]
        holder.textt.text=currentData.title

        Picasso.get().load(currentData.album.cover).into(holder.imagee)

        holder.itemView.setOnClickListener {
            val dialog= Dialog(context)
            dialog.setContentView(R.layout.dialog_design)
            val textId=dialog.findViewById<TextView>(R.id.textView2)
            val exoplayerId=dialog.findViewById<PlayerView>(R.id.exoplayerId)
            textId.text=currentData.title
            exoplayerId.player=player
            val item= MediaItem.fromUri(currentData.preview.toUri())
            player.setMediaItem(item)
            player.prepare()
            player.play()

            dialog.setOnDismissListener {
                player.stop()
                player.clearMediaItems()
            }
            dialog.show()
        }
    }
    class MyViewHolder(itemVIew: View): RecyclerView.ViewHolder(itemVIew) {
        val imagee=itemVIew.findViewById<ShapeableImageView>(R.id.img)
        val textt=itemVIew.findViewById<TextView>(R.id.titlename)

    }
}