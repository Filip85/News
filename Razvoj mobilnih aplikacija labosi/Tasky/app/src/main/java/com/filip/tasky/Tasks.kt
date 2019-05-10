package com.filip.tasky

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity(tableName = "tasks")
data class Tasks(
        @PrimaryKey(autoGenerate = true) val id: Int,
        @ColumnInfo(name = "task") val task: String,
        @ColumnInfo(name= "category") val category: String,
        @ColumnInfo(name = "priority") val priority: String
)

@Entity(tableName = "category")
data class Category(
        @PrimaryKey(autoGenerate = true) val id: Int,
        @ColumnInfo(name = "category") val category: String
)