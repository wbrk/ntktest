package com.example.rssreader.presentation.sourcelist

import com.example.rssreader.data.RssSourceRepository


class SourceListPresenter(private val view: SourceListView) {
    private val sourceRepo = RssSourceRepository()

    fun requestData() {
        val data = sourceRepo.getSources()
        view.showData(data)
    }
}
