package com.heydieandroid.newsantara.ApiConfig

import com.heydieandroid.newsantara.ApiInterface.ApiInterface
import com.heydieandroid.newsantara.constant.Constant
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiConfig {
    companion object {
        fun getApiService(): ApiInterface {
            val build = Retrofit.Builder()
                .baseUrl(Constant.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()

            return build.create(ApiInterface::class.java)
        }
    }
}
