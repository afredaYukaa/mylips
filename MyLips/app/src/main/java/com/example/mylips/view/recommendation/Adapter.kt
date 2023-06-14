package com.example.mylips.view.recommendation

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.mylips.R
import com.example.mylips.retrofit.response.ListStoryItem

class Adapter(private val listStories: List<ListStoryItem>)  : RecyclerView.Adapter<Adapter.ViewHolder>(){

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int) =
        ViewHolder(LayoutInflater.from(viewGroup.context).inflate(R.layout.recommend_item, viewGroup, false))

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val (photo, name, id) = listStories[position]
        Glide.with(holder.itemView.context)
            .load(photo)
            .into(holder.imgPhoto)
        holder.tvColor.text = name

    }

    override fun getItemCount() = listStories.size

    class ViewHolder (view : View) : RecyclerView.ViewHolder(view){
        val tvColor : TextView = view.findViewById(R.id.tvColor)
        val imgPhoto : ImageView = view.findViewById(R.id.imgColor)
    }
}
