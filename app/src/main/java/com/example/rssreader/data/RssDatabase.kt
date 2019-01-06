package com.example.rssreader.data

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [RssSource::class], version = 1)
abstract class RssDatabase : RoomDatabase() {
    abstract fun sourceDao(): RssSourceDao
}