package com.example.rssreader.presentation.source

import com.example.rssreader.domain.entity.RssSource

interface SourceView {
    fun showData(source: RssSource)
}