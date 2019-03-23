package com.example.rssreader.presentation.sourcelist

import com.example.rssreader.addTo
import com.example.rssreader.data.RssSourceRepository
import com.example.rssreader.di.RssDatabaseFactory
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable


class SourceListPresenter(private val view: SourceListView) {
    private val sourceRepo = RssSourceRepository(RssDatabaseFactory.db.sourceDao())
    private val disposables = CompositeDisposable()

    fun requestData() {
        sourceRepo.getSources()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(view::showData)
            .addTo(disposables)
    }

    fun stop() {
        // todo not sure if it's ok to do it this way
        disposables.clear()
    }
}
