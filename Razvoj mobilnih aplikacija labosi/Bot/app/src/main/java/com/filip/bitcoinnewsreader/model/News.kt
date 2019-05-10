package com.filip.bitcoinnewsreader.model

import com.google.gson.annotations.SerializedName

data class News(
    @SerializedName("status") val status: String,
    @SerializedName("totalResults") val totalResults: String,
    @SerializedName("articles") val articles: List<Articles>

)