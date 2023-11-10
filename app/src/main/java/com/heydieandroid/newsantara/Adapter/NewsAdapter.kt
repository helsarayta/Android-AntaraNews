package com.heydieandroid.newsantara.Adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.heydieandroid.newsantara.Model.PostsItem
import com.heydieandroid.newsantara.databinding.RowNewsBinding
import com.heydieandroid.newsantara.utility.Utility
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.util.*
import kotlin.collections.ArrayList

class NewsAdapter(private val listNews:ArrayList<PostsItem>) : RecyclerView.Adapter<NewsAdapter.NewsViewHolder>() {
    private lateinit var onClickCallBack:OnClickCallBack

    fun setOnClickCallBack(onClickCallBack:OnClickCallBack) {
        this.onClickCallBack = onClickCallBack
    }
    interface OnClickCallBack {
        fun itemOnClick(item:PostsItem)
    }

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
        holder.binding.tvDate.text = Utility().convertDateTime(news.pubDate)
        Utility().loadImage(holder.itemView.context, news.thumbnail, holder.binding.imgNews)
//        Glide.with(holder.itemView.context)
//            .load(news.thumbnail)
//            .into(holder.binding.imgNews)

        holder.itemView.setOnClickListener {
            onClickCallBack.itemOnClick(listNews[holder.adapterPosition])
        }
    }
}
