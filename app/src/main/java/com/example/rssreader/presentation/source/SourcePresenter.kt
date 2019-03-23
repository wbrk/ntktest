package com.example.rssreader.presentation.source

import com.example.rssreader.addTo
import com.example.rssreader.data.RssSource
import com.example.rssreader.data.RssSourceRepository
import com.example.rssreader.di.RssDatabaseFactory
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable

class SourcePresenter(private val view: SourceView) {
    private val sourceRepo = RssSourceRepository(RssDatabaseFactory.db.sourceDao())
    private val disposables = CompositeDisposable()

    // todo i think, control flow must be different
    // like fragment tells presenter it's ready
    // then presenter obtains data and sets it to fragment
    fun getById(id: Int) {
        sourceRepo.getById(id)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(view::showData)
            .addTo(disposables)
    }

    fun add(source: RssSource) {
        sourceRepo.add(source)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe()
            .addTo(disposables)
    }

    fun update(source: RssSource) {
        sourceRepo.update(source)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe()
            .addTo(disposables)
    }

    fun delete(source: RssSource) {
        sourceRepo.delete(source)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe()
            .addTo(disposables)
    }

    fun stop() {
        disposables.clear()
    }
}