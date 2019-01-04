package com.example.rssreader

import android.app.Application
import android.content.Context

class App : Application() {
    companion object {
        // todo remove JvmStatic
        // todo use DI?
        @JvmStatic
        lateinit var appContext: Context
        private set
    }

    override fun onCreate() {
        super.onCreate()
        appContext = applicationContext
    }
}