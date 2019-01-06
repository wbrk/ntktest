package com.example.rssreader.data

import androidx.room.*
import io.reactivex.Completable
import io.reactivex.Maybe
import io.reactivex.Single

@Dao
interface RssSourceDao {
    @Query("SELECT * FROM RssSource")
    fun all(): Single<List<RssSource>>

    @Query("SELECT * FROM RssSource WHERE id = :id")
    fun getById(id: Int): Maybe<RssSource>

    @Insert
    fun insert(vararg sources: RssSource): Completable

    @Update
    fun update(vararg sources: RssSource): Completable

    @Delete
    fun delete(vararg sources: RssSource): Completable
}