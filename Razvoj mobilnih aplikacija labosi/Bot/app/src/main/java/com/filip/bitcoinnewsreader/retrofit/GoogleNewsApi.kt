package com.filip.bitcoinnewsreader.retrofit


import com.filip.bitcoinnewsreader.model.News
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

const val BASE_URL = "https://newsapi.org/"

interface GoogleNewsApi {

    @GET("v2/everything")
    fun showNews(@Query("q") q: String,
                 @Query("apiKey") apiKey: String): Call<News>
}