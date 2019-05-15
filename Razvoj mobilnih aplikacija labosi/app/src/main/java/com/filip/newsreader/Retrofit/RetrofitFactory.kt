package com.filip.newsreader.Retrofit

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitFactory {

    val showNewsFromBBC: NewsApi = Retrofit.Builder()    //objekt tipa NewsApi sučelje. Pomoču njega pristupamo metodi unutar sučelja. Retrofit je klasa kroz koju se api sučelj pretvara u objekt koje se može pozvati
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())   //korištenje gsona za convertanje jsona
        .client(OkHttpClient.Builder().build())
        .build()
        .create(NewsApi::class.java)

    //nakon Retrofit.Builder() potrebno je dodati baseUrl kako bi se znalo koji je url odnosno bazni url te build() za stavranje retrofita (to je obavezno)
    //sve ostalo je opcionalno
}