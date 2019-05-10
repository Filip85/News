package com.filip.newsreader.Context

import android.app.Application
import android.content.Context

class MyApplication: Application() {
    companion object {
        lateinit var ApplicationContext: Context
            private set
    }

    override fun onCreate() {
        super.onCreate()
        ApplicationContext = this

    }
}