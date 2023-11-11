package com.heydieandroid.newsantara

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.text.HtmlCompat
import com.heydieandroid.newsantara.Model.PostsItem
import com.heydieandroid.newsantara.constant.Constant
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
            binding.detailTitle.text = HtmlCompat.fromHtml(item.title, HtmlCompat.FROM_HTML_MODE_LEGACY)
            binding.detailDescription.text = HtmlCompat.fromHtml(item.description, HtmlCompat.FROM_HTML_MODE_LEGACY)
            binding.detailLink.text = Constant.ADD_TEXT + item.link
            Utility().loadImage(this, item.thumbnail, binding.detailImg)
        }
    }
}
