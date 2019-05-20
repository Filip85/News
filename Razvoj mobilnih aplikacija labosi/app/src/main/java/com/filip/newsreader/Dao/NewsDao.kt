package com.filip.newsreader.Dao

import android.arch.persistence.room.*
import com.filip.newsreader.DatabaseTable.Articles
import com.filip.newsreader.DatabaseTable.Time

@Dao                       //sadrži metoda za pristup bazi
interface NewsDao {

    @Insert
    fun insertArticles(a: Articles)   //umetanje članaka u bazu

    @Query("SELECT * FROM articles")
    fun getAllArticles(): List<Articles>    //povlačenje vih članaka iz baze, povratan tip je lista Articles-a

    @Delete
    fun deleteAllArticles(a: Articles)        //brisanje svih članaka

    @Query("SELECT COUNT(*) FROM articles")
    fun countArticles(): Int                                         //brojanje koliko ima članaka u bazi

    @Query("SELECT time FROM time")
    fun getTime(): Int                                //povlačenje vremena spremljenog u bazu

    @Insert
    fun insertTime(t: Time)

    @Query("UPDATE time SET time = :newTime WHERE id = 1")
    fun updateTime(newTime: Int)                                         //updatanje vremena gdje je id 1

    @Query("SELECT url FROM articles WHERE id = :id")
    fun getArticle(id: Int): String    //povlačenje

    @Query("SELECT urlToImage FROM articles WHERE id = :id")
    fun getImageOfArticle(id: Int): String

    @Query("SELECT title FROM articles WHERE id = :id")
    fun getTitleOfArticle(id: Int): String

}