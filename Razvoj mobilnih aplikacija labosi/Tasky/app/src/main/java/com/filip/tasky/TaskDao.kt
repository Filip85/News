package com.filip.tasky

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Delete
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query


@Dao
interface TaskDao {

    @Insert
    fun insert(t: Tasks)

    @Query("DELETE FROM tasks WHERE id= :id")
    fun delete(id: Int)

    @Query("SELECT * FROM tasks")
    fun getAll(): List<Tasks>

    @Query("SELECT category FROM category ORDER BY id DESC LIMIT 1")
    fun getCategory() : String

    @Query("SELECT category FROM category")
    fun getAllCategory() : List<String>

    @Insert
    fun insertC(c: Category)

    /*@Query("SELECT * FROM cat")
    fun getCategory(category: Category)*/
}