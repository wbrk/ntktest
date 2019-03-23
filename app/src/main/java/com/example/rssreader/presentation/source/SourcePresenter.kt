package com.example.rssreader.presentation.source

import com.example.rssreader.domain.entity.RssSource
import com.example.rssreader.data.repository.RssSourceRepository
import com.example.rssreader.di.RssDatabaseFactory
import com.example.rssreader.presentation.BasePresenter
import io.reactivex.android.schedulers.AndroidSchedulers

class SourcePresenter(private val view: SourceView) : BasePresenter() {
    private val sourceRepo = RssSourceRepository(RssDatabaseFactory.db.sourceDao())

    // todo i think, control flow must be different
    // like fragment tells presenter it's ready
    // then presenter obtains data and sets it to fragment
    fun getById(id: Int) {
        sourceRepo.getById(id)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(view::showData)
            .clearOnDestroy()
    }

    fun add(source: RssSource) {
        sourceRepo.add(source)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe()
            .clearOnDestroy()
    }

    fun update(source: RssSource) {
        sourceRepo.update(source)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe()
            .clearOnDestroy()
    }

    fun delete(source: RssSource) {
        sourceRepo.delete(source)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe()
            .clearOnDestroy()
    }
}