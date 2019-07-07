package com.example.rssreader.presentation.feed

import com.example.rssreader.domain.entity.RssItem

interface FeedView {
    fun showProgress()
    fun hideProgress()

    fun showData(data: List<RssItem>)
    fun showError()

    fun openDetails(url: String)
}
