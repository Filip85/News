package com.filip.bazairecyclerview

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query

@Dao
interface BookDao {

    @Insert
    fun insert(book: Book)

    @Query("SELECT * FROM books")
    fun getAll(): List<Book>
}