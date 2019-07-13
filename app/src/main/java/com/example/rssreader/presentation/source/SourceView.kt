package com.example.rssreader.presentation.source

import com.example.rssreader.domain.entity.RssSource

interface SourceView {
    fun showSource(source: RssSource)

    fun showNewSourceTitle()

    fun goBack()
}