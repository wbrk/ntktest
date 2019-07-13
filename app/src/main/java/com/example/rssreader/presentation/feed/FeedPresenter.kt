package com.example.rssreader.presentation.feed

import android.net.Uri
import com.example.rssreader.data.repository.RssItemRepository
import com.example.rssreader.di.RssServiceFactory
import com.example.rssreader.domain.entity.RssItem
import com.example.rssreader.logInfo
import com.example.rssreader.presentation.BasePresenter
import io.reactivex.android.schedulers.AndroidSchedulers

class FeedPresenter(private val view: FeedView) : BasePresenter() {
    private val itemRepository = RssItemRepository(RssServiceFactory.service)
    private lateinit var data: List<RssItem>

    override fun start() {
        load()
    }

    fun onRefresh() {
        stop()
        load()
    }

    fun onItemClick(position: Int) {
        view.openDetails(data[position].link)
    }

    private fun load() {
        itemRepository.loadAllFrom(Uri.parse("https://meduza.io/rss/all/"))
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { view.showProgress() }
            .doOnTerminate { view.hideProgress() }
            .subscribe(this::onSuccess, this::onError)
            .clearOnDestroy()
    }

    private fun onSuccess(data: List<RssItem>) {
        this.data = data
        view.showData(data)
    }

    private fun onError(t: Throwable) {
        logInfo("onError: error: $t", t)
        view.showError()
    }
}