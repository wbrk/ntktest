package com.example.rssreader.presentation.source

import com.example.rssreader.data.RssSource
import com.example.rssreader.data.RssSourceRepository

class SourcePresenter {
    private val sourceRepo: RssSourceRepository = RssSourceRepository()

    fun getById(id: Int) = sourceRepo.getById(id)

    fun add(source: RssSource) = sourceRepo.add(source)

    fun update(source: RssSource) = sourceRepo.update(source)

    fun delete(source: RssSource) = sourceRepo.delete(source)
}