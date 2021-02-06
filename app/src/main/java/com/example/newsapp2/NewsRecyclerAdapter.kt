package com.example.newsapp2

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import api.Article
import com.bumptech.glide.Glide

class NewsRecyclerAdapter(var newsList: List<Article>?) : RecyclerView.Adapter<NewsRecyclerAdapter.ViewHolder>() {

    private var clickListener: ClickListener? = null
  inner  class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) , View.OnClickListener {
        val title: TextView = itemView.findViewById(R.id.title)
        val source_name: TextView = itemView.findViewById(R.id.source_name)
        val  date: TextView = itemView.findViewById(R.id.date)
        val  desc : TextView = itemView.findViewById(R.id.desc)
        val image : ImageView = itemView.findViewById(R.id.image)
        init {
            if (clickListener != null) {
                itemView.setOnClickListener(this)
            }
        }
        override fun onClick(v: View?) {
            var arraylist = ArrayList<String>()
            val newsItem: Article = newsList!!.get(position)
            arraylist.add(newsItem.urlToImage)
            arraylist.add(newsItem.title)
            arraylist.add(newsItem.content)
            if (v != null) {
                clickListener?.onItemClick(v,adapterPosition)
                val context = itemView.context
                val showPhotoIntent = Intent(context, Details::class.java)
                showPhotoIntent.putStringArrayListExtra("keyString",arraylist)
                context.startActivity(showPhotoIntent)
            }
        }

        //val view = itemView

    }
    fun setOnItemClickListener(clickListener: ClickListener) {
        this.clickListener = clickListener
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
    fun getItem(position: Int): Article? {
        val newsItem: Article = newsList!!.get(position)
        return if (newsList != null) newsItem else null

    }
    override fun getItemCount(): Int {
        return if (newsList == null) 0 else newsList!!.size
    }
    fun changeData(newsList: List<Article>) {
        this.newsList = newsList
        notifyDataSetChanged()
    }
    interface ClickListener {
        fun onItemClick(v: View,position: Int)
    }
}