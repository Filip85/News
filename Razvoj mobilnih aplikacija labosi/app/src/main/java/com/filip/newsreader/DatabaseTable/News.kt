package com.filip.newsreader.DatabaseTable


data class News(
    val status: String,
    val source: String,
    val sortBy: String,
    val articles: List<Articles>
)



