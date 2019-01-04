package com.example.rssreader.data

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import com.example.rssreader.App

@Database(entities = [RssSource::class], version = 1)
abstract class RssDatabase : RoomDatabase() {
    companion object {
        // todo remove JvmStatic
        // todo remove instantiation (use DI)
        @JvmStatic
        val instance: RssDatabase by lazy {
            Room.databaseBuilder(App.appContext, RssDatabase::class.java, "db")
                .allowMainThreadQueries() // todo remove me
                .build()
        }
    }

    abstract fun sourceDao(): RssSourceDao
}