package com.filip.bazairecyclerview

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase

@Database(version = 1, entities = arrayOf(Book::class))
abstract class BookDatabase: RoomDatabase() {

    abstract fun bookDao(): BookDao

    companion object {
        private val instance by lazy {
            Room.databaseBuilder(MyApplication.ApplicationContext, BookDatabase::class.java, "weather")
                .allowMainThreadQueries()
                .build()
        }

        val bookDao: BookDao by lazy { instance.bookDao() }
    }


}