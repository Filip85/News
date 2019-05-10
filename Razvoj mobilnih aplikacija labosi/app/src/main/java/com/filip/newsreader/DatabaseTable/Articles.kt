package com.filip.newsreader.DatabaseTable

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity(tableName = "articles")
data class Articles(
        @PrimaryKey val id: Int,
        val title: String,
        val description: String,
        val url: String,
        val urlToImage: String
)
