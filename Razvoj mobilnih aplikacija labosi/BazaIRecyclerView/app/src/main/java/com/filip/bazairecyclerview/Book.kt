package com.filip.bazairecyclerview

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity(tableName = "books")
data class Book(
    @PrimaryKey val author: String,
    val title: String
)