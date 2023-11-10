package com.heydieandroid.newsantara.utility

import android.content.Context
import android.widget.ImageView
import com.bumptech.glide.Glide
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.util.*

class Utility {
     fun convertDateTime(pubDate: String?): CharSequence? {
        val formatter = DateTimeFormatter.ofPattern("EEEE, dd MMMM yyyy HH:mm", Locale("id"))
        val parse = Instant.parse(pubDate)
        val local = LocalDateTime.ofInstant(parse, ZoneId.systemDefault())
        return "${local.format(formatter)} WIB"
    }

    fun loadImage(context: Context, linkImage:String?, imgView: ImageView) {
        Glide.with(context)
            .load(linkImage)
            .into(imgView)
    }
}
