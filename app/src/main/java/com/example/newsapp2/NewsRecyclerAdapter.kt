package com.example.newsapp2

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import api.Article
import com.bumptech.glide.Glide

class NewsRecyclerAdapter(var newsList: List<Article>?) : RecyclerView.Adapter<NewsRecyclerAdapter.ViewHolder>() {


    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val title: TextView = itemView.findViewById(R.id.title)
        val source_name: TextView = itemView.findViewById(R.id.source_name)
       val  date: TextView = itemView.findViewById(R.id.date)
      val  desc : TextView = itemView.findViewById(R.id.desc)
        val image : ImageView = itemView.findViewById(R.id.image)

        //val view = itemView

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_news, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val newsItem: Article = newsList!!.get(position)

        holder.title.setText(newsItem.title)
        holder.source_name.setText(newsItem.source.name)
        holder.date.setText(newsItem.publishedAt)
        holder.desc.setText(newsItem.description)
        Glide.with(holder.itemView)
            .load(newsItem.urlToImage)
            .into(holder.image)
    }

    override fun getItemCount(): Int {
        return if (newsList == null) 0 else newsList!!.size
    }
    fun changeData(newsList: List<Article>) {
        this.newsList = newsList
        notifyDataSetChanged()
    }

}