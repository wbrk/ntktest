package com.example.rssreader.di

import androidx.room.Room
import com.example.rssreader.App
import com.example.rssreader.data.RssDatabase

object RssDatabaseFactory {
    val db: RssDatabase by lazy {
        Room.databaseBuilder(App.appContext, RssDatabase::class.java, "db")
            .build()
    }
}