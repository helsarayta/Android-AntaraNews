package com.heydieandroid.newsantara.ApiInterface


import com.heydieandroid.newsantara.Model.News
import com.heydieandroid.newsantara.constant.Constant
import retrofit2.Call
import retrofit2.http.GET

interface ApiInterface {
    @GET(Constant.SUFFIX_ENDPOINT)
    fun getLatestNews2() : Call<News>

}
