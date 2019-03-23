package com.example.rssreader.data

import com.example.rssreader.di.RssDatabaseFactory
import io.reactivex.Completable
import io.reactivex.Maybe
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers

class RssSourceRepository(private val sourceDao: RssSourceDao) {
    fun getSources(): Single<List<RssSource>> =
        sourceDao.all()
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