package com.heydieandroid.newsantara

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.heydieandroid.newsantara.constant.Constant
import com.heydieandroid.newsantara.databinding.ActivityAboutBinding

class AboutActivity : AppCompatActivity() {
    private lateinit var binding:ActivityAboutBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAboutBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.btnLinkedin.setOnClickListener {
            val toLinkdin =
                Intent(Intent.ACTION_VIEW, Uri.parse(Constant.URL_LINKEDIN))
            startActivity(toLinkdin)

        }
    }
}
