package com.filip.converter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.pictures.view.*

class PictureAdapter(val pictires: MutableList<Picture>, val pictureListener: PictureInteractionListener): RecyclerView.Adapter<PictureHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PictureHolder {
        val pictureView = LayoutInflater.from(parent.context).inflate(R.layout.pictures, parent, false)
        val pictureHolder = PictureHolder(pictureView)
        return pictureHolder
    }

    override fun getItemCount(): Int {
        return pictires.size
    }

    override fun onBindViewHolder(holder: PictureHolder, position: Int) {
        val picture = pictires[position]
        holder.bind(picture, pictureListener)
    }
}

class PictureHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
    /*val pic = listOf(R.drawable.weightpicture, R.drawable.temperature, R.drawable.distance, R.drawable.time)*/
    fun bind(pictures: Picture, pictureListener: PictureInteractionListener){

        itemView.pictureID.setImageResource(pictures.pic)
        /*val pos = (pictures.id) - 1;
        Picasso.get().load(pic[pos]).fit().error(android.R.drawable.stat_notify_error).into(itemView.pictureID)*/
        itemView.setOnClickListener{ pictureListener.newActivity(pictures.id)}
    }
}