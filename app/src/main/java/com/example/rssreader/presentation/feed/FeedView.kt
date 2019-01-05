package com.example.rssreader.presentation.feed

import com.example.rssreader.data.RssItem

interface FeedView {
    fun showData(data: List<RssItem>)
    fun showError()
}
