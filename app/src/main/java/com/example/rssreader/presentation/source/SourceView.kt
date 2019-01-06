package com.example.rssreader.presentation.source

import com.example.rssreader.data.RssSource

interface SourceView {
    fun showData(source: RssSource)
}