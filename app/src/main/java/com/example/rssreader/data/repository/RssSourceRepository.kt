package com.example.rssreader.data.repository

import com.example.rssreader.domain.entity.RssSource
import com.example.rssreader.data.db.RssSourceDao
import io.reactivex.Completable
import io.reactivex.Maybe
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers

class RssSourceRepository(private val sourceDao: RssSourceDao) {
    val sources: Single<List<RssSource>>
        get() = sourceDao.all()
            .subscribeOn(Schedulers.io())

    fun getById(id: Int): Maybe<RssSource> =
        sourceDao.getById(id)
            .subscribeOn(Schedulers.io())

    fun add(source: RssSource): Completable =
        sourceDao.insert(source)
            .subscribeOn(Schedulers.io())

    fun update(source: RssSource): Completable =
        sourceDao.update(source)
            .subscribeOn(Schedulers.io())

    fun delete(source: RssSource): Completable =
        sourceDao.delete(source)
            .subscribeOn(Schedulers.io())
}