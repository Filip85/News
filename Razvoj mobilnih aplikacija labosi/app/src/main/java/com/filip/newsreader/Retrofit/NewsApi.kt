package com.filip.newsreader.Retrofit

import com.filip.newsreader.DatabaseTable.News
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

const val BASE_URL = "https://newsapi.org/"
const val API_KEY = "6946d0c07a1c4555a4186bfcade76398"
const val SOURCE = "bbc-news"
const val SORT_BY = "top"

interface NewsApi {     //naše vlastito sučelje koje pretstavlja web uslugu

    @GET("v1/articles")                //GET anotacija pruža pristup za čitanje dio url-a (relative url) koji se veže na BASE_URL
    fun showNews(@Query("source") source: String,                    //fumkcija za dohvačanje podataka sa api-ja
                 @Query("sortBy") sortBy: String,
                 @Query("apiKey") apiKey: String): Call<News>
}