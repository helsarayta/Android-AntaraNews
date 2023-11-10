package com.heydieandroid.newsantara.ApiConfig

import com.heydieandroid.newsantara.ApiInterface.ApiInterface
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiConfig {
    companion object {
        val BASE_URL = "https://api-berita-indonesia.vercel.app/antara/"

        fun getApiService(): ApiInterface {
            val build = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()

            return build.create(ApiInterface::class.java)
        }
    }
}
