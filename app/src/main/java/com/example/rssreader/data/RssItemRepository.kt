package com.example.rssreader.data

import android.net.Uri
import com.example.rssreader.di.RssServiceFactory
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers

class RssItemRepository {
    fun loadAllFrom(uri: Uri): Single<List<RssItem>> =
        RssServiceFactory.service
            .getData(uri.toString())
            .subscribeOn(Schedulers.io())
            .map { it.items }
}