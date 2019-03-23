package com.example.rssreader.data

import android.net.Uri
import com.example.rssreader.di.RssServiceFactory
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers

class RssItemRepository(private val service: RssService) {
    fun loadAllFrom(uri: Uri): Single<List<RssItem>> =
        service
            .getData(uri.toString())
            .subscribeOn(Schedulers.io())
            .map { it.items }
            .flattenAsObservable { list -> list }
            .filter(::allFieldsSet)
            .map(RssItemConverter::convert)
            .toList()

    private fun allFieldsSet(item: RssItemModel): Boolean = with(item) {
        !title.isNullOrEmpty() && !link.isNullOrEmpty() && !pubDate.isNullOrEmpty()
    }
}