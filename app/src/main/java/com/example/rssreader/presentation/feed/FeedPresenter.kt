package com.example.rssreader.presentation.feed

import android.net.Uri
import com.example.rssreader.data.repository.RssItemRepository
import com.example.rssreader.di.RssServiceFactory
import com.example.rssreader.logInfo
import com.example.rssreader.presentation.BasePresenter
import io.reactivex.android.schedulers.AndroidSchedulers

class FeedPresenter(private val view: FeedView) : BasePresenter() {
    private val itemRepository = RssItemRepository(RssServiceFactory.service)

    private fun onError(t: Throwable) {
        logInfo("onError: error: $t", t)
        view.showError()
    }

    fun requestData() {
        itemRepository.loadAllFrom(Uri.parse("https://meduza.io/rss/all/"))
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(view::showData, this::onError)
            .clearOnDestroy()
    }
}