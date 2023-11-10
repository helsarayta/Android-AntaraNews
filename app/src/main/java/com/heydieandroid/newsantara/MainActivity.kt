package com.heydieandroid.newsantara

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.heydieandroid.newsantara.Adapter.NewsAdapter
import com.heydieandroid.newsantara.ApiConfig.ApiConfig
import com.heydieandroid.newsantara.Model.News
import com.heydieandroid.newsantara.Model.PostsItem
import com.heydieandroid.newsantara.databinding.ActivityMainBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private var newsListData = ArrayList<PostsItem>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.rvList.setHasFixedSize(true)
        getNews()

    }

    private fun getNews() {
        val arrayPosts = ArrayList<PostsItem>()
        fetchData {
            listData ->
            for (data in listData!!) {
                val postsItem = PostsItem(
                    data.thumbnail,
                    data.link,
                    data.description,
                    data.title,
                    data.pubDate
                )

                arrayPosts.add(postsItem)

            }

            newsListData.addAll(arrayPosts)
            showRecycleList(newsListData)
        }
    }

    private fun showRecycleList(newsListData: java.util.ArrayList<PostsItem>) {
        binding.rvList.layoutManager = LinearLayoutManager(this)
        val newsAdapter = NewsAdapter(newsListData)
        binding.rvList.adapter = newsAdapter
    }


    private fun fetchData(callback:(List<PostsItem>?) -> Unit) {
        ApiConfig.getApiService().getLatestNews2().enqueue(object : Callback<News>{
            override fun onResponse(call: Call<News>, response: Response<News>) {
                if(response.isSuccessful) {
                    val postsNews = response.body()?.data?.posts
                    callback(postsNews)
                } else {
                    Log.i("error", "No DATA !!!")
                }
            }

            override fun onFailure(call: Call<News>, t: Throwable) {
                Log.i("DATA", t.message.toString())
            }

        })
    }
}
