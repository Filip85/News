package com.filip.bitcoinnewsreader.retrofit

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object Networking{
    val showBitcoinNews: GoogleNewsApi = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .client(OkHttpClient.Builder().build())
        .baseUrl(BASE_URL)
        .build()
        .create(GoogleNewsApi::class.java)
}

/*object ConverterFactory{
    val converterFactory = GsonConverterFactory.create()
}*/

/*object HttpClient{
    val client = OkHttpClient.Builder()
        .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
        .build()
}*/

