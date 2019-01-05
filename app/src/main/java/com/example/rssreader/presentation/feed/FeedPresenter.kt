package com.example.rssreader.presentation.feed

import android.net.Uri
import android.util.Log
import com.example.rssreader.data.DataRetriever

class FeedPresenter(private val view: FeedView) {
    companion object {
        private const val TAG = "FeedPresenter"
    }

    private val retriever = DataRetriever(Uri.parse("https://meduza.io/rss/all/"),
        view::showData, this::onError)

    private fun onError(responseCode: Int, t: Throwable?) {
        Log.i(TAG, "onError: code: $responseCode")
        Log.i(TAG, "onError: error: $t")
        view.showError()
    }

    fun requestData() = retriever.load()
    fun stop() = retriever.cancel()
}