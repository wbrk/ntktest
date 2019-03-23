package com.example.rssreader.data.repository

import android.net.Uri
import com.example.rssreader.data.RssItemConverter
import com.example.rssreader.data.model.RssItemModel
import com.example.rssreader.data.service.RssService
import com.example.rssreader.domain.entity.RssItem
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