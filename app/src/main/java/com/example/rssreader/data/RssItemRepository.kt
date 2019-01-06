package com.example.rssreader.data

import android.net.Uri
import com.example.rssreader.di.RssServiceFactory
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers

class RssItemRepository(private val uri: Uri) {
    private val service: RssService = RssServiceFactory.service

    fun load(): Single<List<RssItem>> =
        service.getData(uri.toString())
            .subscribeOn(Schedulers.io())
            .map { it.items }
}