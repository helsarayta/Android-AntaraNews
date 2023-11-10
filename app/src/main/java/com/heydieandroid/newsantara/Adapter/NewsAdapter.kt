package com.heydieandroid.newsantara.Adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.heydieandroid.newsantara.Model.PostsItem
import com.heydieandroid.newsantara.databinding.RowNewsBinding
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.format.DateTimeFormatter

class NewsAdapter(private val listNews:ArrayList<PostsItem>) : RecyclerView.Adapter<NewsAdapter.NewsViewHolder>() {
    class NewsViewHolder(val binding: RowNewsBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        return NewsViewHolder(RowNewsBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun getItemCount(): Int {
        return listNews.size
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        val news = listNews[position]
        holder.binding.tvTitle.text = news.title
        holder.binding.tvDate.text = convertDateTime(news.pubDate)

        Glide.with(holder.itemView.context)
            .load(news.thumbnail)
            .into(holder.binding.imgNews)
    }

    private fun convertDateTime(pubDate: String?): CharSequence? {
        val formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm")
        val parse = Instant.parse(pubDate)
        val local = LocalDateTime.ofInstant(parse, ZoneId.systemDefault())
        return local.format(formatter)
    }
}
