package com.heydieandroid.newsantara

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import com.heydieandroid.newsantara.Model.PostsItem
import com.heydieandroid.newsantara.databinding.ActivityDetailBinding
import com.heydieandroid.newsantara.utility.Utility

class DetailActivity : AppCompatActivity() {
    private lateinit var binding:ActivityDetailBinding
    companion object {
        val NEWS_DATA = "news_data"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val item = if (Build.VERSION.SDK_INT >= 33) {
            intent.getParcelableExtra(NEWS_DATA, PostsItem::class.java)
        } else {
            @Suppress("DEPRECATION")
            intent.getParcelableExtra(NEWS_DATA)
        }

        if(item != null) {
            binding.detailTime.text = Utility().convertDateTime(item.pubDate)
            binding.detailTitle.text = item.title
            binding.detailDescription.text = item.description
            binding.detailLink.text = "Selengkapnya... ${item.link}"
            Utility().loadImage(this, item.thumbnail, binding.detailImg)
        }
    }
}
