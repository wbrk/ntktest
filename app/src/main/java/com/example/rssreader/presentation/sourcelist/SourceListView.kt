package com.example.rssreader.presentation.sourcelist

import com.example.rssreader.data.RssSource

interface SourceListView {
    fun showData(data: List<RssSource>)
}
