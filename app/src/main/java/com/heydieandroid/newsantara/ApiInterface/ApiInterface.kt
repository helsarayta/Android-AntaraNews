package com.heydieandroid.newsantara.ApiInterface


import com.heydieandroid.newsantara.Model.News
import retrofit2.Call
import retrofit2.http.GET

interface ApiInterface {
    @GET("terbaru/")
    fun getLatestNews2() : Call<News>

}
