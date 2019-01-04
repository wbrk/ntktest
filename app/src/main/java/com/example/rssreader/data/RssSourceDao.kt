package com.example.rssreader.data

import android.arch.persistence.room.*

@Dao
interface RssSourceDao {
    @Query("SELECT * FROM RssSource")
    fun all(): List<RssSource>

    @Query("SELECT * FROM RssSource WHERE id = :id")
    fun getById(id: Int): RssSource

    @Insert
    fun insert(vararg sources: RssSource)

    @Update
    fun update(vararg sources: RssSource)

    @Delete
    fun delete(vararg sources: RssSource)
}