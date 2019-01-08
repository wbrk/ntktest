package com.example.rssreader.presentation.feed

import android.net.Uri
import android.util.Log
import com.example.rssreader.addTo
import com.example.rssreader.data.RssItemRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable

class FeedPresenter(private val view: FeedView) {
    companion object {
        private const val TAG = "FeedPresenter"
    }

    private val itemRepository = RssItemRepository()
    private val disposables = CompositeDisposable()

    private fun onError(t: Throwable) {
        Log.i(TAG, "onError: error: $t", t)
        view.showError()
    }

    fun requestData() {
        itemRepository.loadAllFrom(Uri.parse("https://meduza.io/rss/all/"))
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(view::showData, this::onError)
            .addTo(disposables)
    }

    fun stop() = disposables.clear()
}