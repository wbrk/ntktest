package com.example.rssreader.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.rssreader.domain.entity.RssSource

@Database(entities = [RssSource::class], version = 1)
abstract class RssDatabase : RoomDatabase() {
    abstract fun sourceDao(): RssSourceDao
}