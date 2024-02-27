package com.example.sangeet.Adapters

import android.app.Dialog
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.net.toUri
import androidx.media3.common.MediaItem
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.ui.PlayerView
import androidx.recyclerview.widget.RecyclerView
import com.example.sangeet.R
import com.google.android.material.imageview.ShapeableImageView
import java.io.File

class StorageMusicAdapter (private val context: Context, private val songs: List<File>) : RecyclerView.Adapter<StorageMusicAdapter.ViewHolder>() {

    private lateinit var player:ExoPlayer

    init {
        player = ExoPlayer.Builder(context).build()
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val View= LayoutInflater.from(context).inflate(R.layout.activity_design,parent,false)
        return ViewHolder(View)
    }

    override fun getItemCount(): Int {
        return songs.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val Song=songs[position]
        holder.textt.text=Song.name


        holder.itemView.setOnClickListener {
            val dialog=Dialog(context)
            dialog.setContentView(R.layout.dialog_design)
            val textId=dialog.findViewById<TextView>(R.id.textView2)
            val exoplayerId=dialog.findViewById<PlayerView>(R.id.exoplayerId)
            textId.text=Song.name
            exoplayerId.player=player
            val item=MediaItem.fromUri(Song.toUri())
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
    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val imagee=itemView.findViewById<ShapeableImageView>(R.id.img)
        val textt=itemView.findViewById<TextView>(R.id.titlename)

    }
}