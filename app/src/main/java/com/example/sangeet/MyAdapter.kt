package com.example.sangeet

import android.app.Activity
import android.media.MediaPlayer
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.SeekBar
import android.widget.TextView
import android.widget.Toast
import androidx.core.net.toUri
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import java.util.logging.Handler

class MyAdapter(val context:Activity,val dataList:List<Data>)
    : RecyclerView.Adapter<MyAdapter.MyViewHolder>(){
    private var mediaPlayer: MediaPlayer? = null
    private var lastplayed =-1
    private  var lastskbar : SeekBar?=null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemVew=LayoutInflater.from(context).inflate(R.layout.activity_design,parent,false)
        return MyViewHolder(itemVew)
    }
    override fun getItemCount(): Int {
        return dataList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentData=dataList[position]
        holder.strt.setBackgroundResource(R.drawable.start_icon)
        holder.textt.text=currentData.title
        holder.sekbar.max=currentData.duration
        holder.sekbar.tag=position
        Picasso.get().load(currentData.album.cover).into(holder.imagee)


        holder.strt.setOnClickListener {
            if(lastplayed==position){
                if(mediaPlayer?.isPlaying==true){
                    mediaPlayer?.pause()
                    holder.strt.setBackgroundResource(R.drawable.stop_icon)
                }else{
                    mediaPlayer?.start()
                    holder.strt.setBackgroundResource(R.drawable.start_icon)

                }
            }else{
                mediaPlayer?.release()
                mediaPlayer=MediaPlayer()
                mediaPlayer?.setDataSource(context,currentData.preview.toUri())
                mediaPlayer?.prepare()
                mediaPlayer?.start()
                holder.strt.setBackgroundResource(R.drawable.start_icon)
                lastplayed=position
                lastskbar?.visibility=View.GONE
                holder.sekbar.visibility=View.VISIBLE
                lastskbar=holder.sekbar
                mediaPlayer?.setOnCompletionListener{
                    holder.sekbar.visibility=View.GONE
                }

                mediaPlayer?.setOnPreparedListener {
                    holder.sekbar.max=mediaPlayer!!.duration
                    holder.sekbar.postDelayed(object : Runnable {
                        override fun run() {
                            if (mediaPlayer != null){
                                holder.sekbar.progress=mediaPlayer!!.currentPosition
                                holder.sekbar.postDelayed(this,1000)
                            }
                        }
                    },1000)

                }
            }
        }

        if(lastplayed==position){
          holder.sekbar.visibility=View.VISIBLE
            lastskbar=holder.sekbar
        }else{
            holder.sekbar.visibility=View.GONE}

        holder.sekbar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener{
            override fun onProgressChanged(p0: SeekBar?, p1: Int, p2: Boolean) {
                if(p2){
                    val skbrposition=p0?.tag as?Int
                    if(skbrposition !=null && skbrposition==position) {
                        mediaPlayer?.seekTo(p1)
                    }
                }
            }
            override fun onStartTrackingTouch(p0: SeekBar?) {}
            override fun onStopTrackingTouch(p0: SeekBar?) {}
        })
    }
    class MyViewHolder(itemVIew:View): RecyclerView.ViewHolder(itemVIew) {
        val imagee=itemVIew.findViewById<ImageView>(R.id.img)
        val textt=itemVIew.findViewById<TextView>(R.id.titlename)
        val strt=itemVIew.findViewById<ImageButton>(R.id.start)
        val sekbar=itemVIew.findViewById<SeekBar>(R.id.seekbr)
    }
}

private fun SeekBar?.postDelayed(onSeekBarChangeListener: SeekBar.OnSeekBarChangeListener, l: Int) {}
