package com.example.sangeet.Adapters

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.sangeet.ApiMusicFetchActivity
import com.example.sangeet.R
import com.google.android.material.imageview.ShapeableImageView
import com.squareup.picasso.Picasso

class ArtistsAdapter(private var context: Context,private var list:List<String>): RecyclerView.Adapter<ArtistsAdapter.MyViewHolder>() {
val imgList= listOf(R.drawable.eminem,R.drawable.drake,R.drawable.taylor, R.drawable.snoop,R.drawable.weekend)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArtistsAdapter.MyViewHolder {
        val view:View=LayoutInflater.from(context).inflate(R.layout.artist_container,parent,false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: ArtistsAdapter.MyViewHolder, position: Int) {
        holder.textt.text=list[position]
        Picasso.get().load(imgList[position]).into(holder.imagee)

        holder.itemView.setOnClickListener {
            val str=list.get(position).toString()
            val intent=Intent(context,ApiMusicFetchActivity::class.java)
            intent.putExtra("searchkey",str)
            startActivity(context,intent,null)
        }

    }
    override fun getItemCount(): Int {
        return list.size
    }
    class MyViewHolder(itemView:View): RecyclerView.ViewHolder(itemView) {
        val imagee=itemView.findViewById<ShapeableImageView>(R.id.shapeableImageView)
        val textt=itemView.findViewById<TextView>(R.id.text)
    }
}