package com.example.rssreader.data

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.rssreader.App

@Database(entities = [RssSource::class], version = 1)
abstract class RssDatabase : RoomDatabase() {
    companion object {
        // todo remove instantiation (use DI)
        val instance: RssDatabase by lazy {
            Room.databaseBuilder(App.appContext, RssDatabase::class.java, "db")
                .allowMainThreadQueries() // todo remove me
                .build()
        }
    }

    abstract fun sourceDao(): RssSourceDao
}