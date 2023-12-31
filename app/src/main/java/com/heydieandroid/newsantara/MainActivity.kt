package com.heydieandroid.newsantara

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.heydieandroid.newsantara.Adapter.NewsAdapter
import com.heydieandroid.newsantara.ApiConfig.ApiConfig
import com.heydieandroid.newsantara.databinding.ActivityMainBinding
import com.heydieandroid.newsantara.Model.News
import com.heydieandroid.newsantara.Model.PostsItem
import com.heydieandroid.newsantara.constant.Constant
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

        newsAdapter.setOnClickCallBack(object : NewsAdapter.OnClickCallBack{
            override fun itemOnClick(item: PostsItem) {
                showSelectedNews(item)
            }

        })
    }

    private fun showSelectedNews(item: PostsItem) {
        val toDetail = Intent(this@MainActivity, DetailActivity::class.java)
        toDetail.putExtra(DetailActivity.NEWS_DATA, item)
        startActivity(toDetail)
    }

    private fun fetchData(callback:(List<PostsItem>?) -> Unit) {
        ApiConfig.getApiService().getLatestNews2().enqueue(object : Callback<News>{
            override fun onResponse(call: Call<News>, response: Response<News>) {
                if(response.isSuccessful) {
                    val postsNews = response.body()?.data?.posts
                    callback(postsNews)
                } else {
                    Log.i(Constant.TAG_ERROR, Constant.NO_DATA)
                }
            }

            override fun onFailure(call: Call<News>, t: Throwable) {
                Log.i(Constant.TAG_ERROR, t.message.toString())
            }

        })
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.action_about) {
            val toAbout = Intent(this@MainActivity, AboutActivity::class.java)
            startActivity(toAbout)
        }
        return super.onOptionsItemSelected(item)
    }
}
