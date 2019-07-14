package com.example.rssreader.presentation.sourcelist

import com.example.rssreader.domain.entity.RssSource

interface SourceListView {
    fun showData(data: List<RssSource>)

    fun showMessageOnItemRemove()

    fun scrollTo(position: Int)

    fun openEditSource(sourceId: Int)

    fun openNewSource()
}
