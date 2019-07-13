package com.example.rssreader.presentation.source

import com.example.rssreader.domain.entity.RssSource

interface SourceView {
    fun showExistingSource(source: RssSource)

    fun showNewSource()

    fun goBack()
}