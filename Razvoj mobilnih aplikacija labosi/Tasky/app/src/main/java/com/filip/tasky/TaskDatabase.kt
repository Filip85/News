package com.filip.tasky

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.Room.databaseBuilder
import android.arch.persistence.room.RoomDatabase


@Database(version = 1, entities = arrayOf(Tasks::class, Category::class))
abstract class TaskDatabase: RoomDatabase() {

    abstract fun taskDao(): TaskDao
    companion object {
        private val instance by lazy {
            Room.databaseBuilder(MyApplication.ApplicationContext, TaskDatabase::class.java, "task")
                    .allowMainThreadQueries()
                    .fallbackToDestructiveMigration()
                    .build()
        }

        val taskDao: TaskDao by lazy { instance.taskDao() }

    }

}