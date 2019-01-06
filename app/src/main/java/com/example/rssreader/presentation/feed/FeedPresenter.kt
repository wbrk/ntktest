package com.example.rssreader.presentation.feed

import android.net.Uri
import android.util.Log
import com.example.rssreader.addTo
import com.example.rssreader.data.DataRetriever
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable

class FeedPresenter(private val view: FeedView) {
    companion object {
        private const val TAG = "FeedPresenter"
    }

    private val retriever = DataRetriever(Uri.parse("https://meduza.io/rss/all/"))
    private val disposables = CompositeDisposable()

    private fun onError(t: Throwable) {
        Log.i(TAG, "onError: error: $t")
        view.showError()
    }

    fun requestData() {
        retriever.load()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(view::showData, this::onError)
            .addTo(disposables)
    }

    fun stop() = disposables.clear()
}