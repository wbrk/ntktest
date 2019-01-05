package com.example.rssreader

import android.app.Application
import android.content.Context

class App : Application() {
    companion object {
        // todo use DI?
        lateinit var appContext: Context
        private set
    }

    override fun onCreate() {
        super.onCreate()
        appContext = applicationContext
    }
}