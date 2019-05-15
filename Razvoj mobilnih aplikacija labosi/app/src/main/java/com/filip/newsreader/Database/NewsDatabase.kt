package com.filip.newsreader.Database

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import com.filip.newsreader.Dao.NewsDao
import com.filip.newsreader.DatabaseTable.Articles
import com.filip.newsreader.DatabaseTable.Time
import com.filip.newsreader.Cont.MyApplication

@Database(version = 3, entities = arrayOf(Articles::class, Time::class))
abstract class NewsDatabase: RoomDatabase() {                         //baza koja nasljeđuje RoomDatabse

    abstract fun newsDao(): NewsDao
    companion object {
        private val instance by lazy {   //kriranje instance samo jednom
            Room.databaseBuilder(MyApplication.ApplicationContext, NewsDatabase::class.java, "news")   //definiranje imena baze
                    .allowMainThreadQueries()
                    .fallbackToDestructiveMigration()
                    .build()
        }

        val newsDao: NewsDao by lazy { instance.newsDao() }   // pomocu newsDao pristupamo metodama u NewsDao

    }

}