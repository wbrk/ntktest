package com.example.rssreader.presentation.sourcelist

import com.example.rssreader.addTo
import com.example.rssreader.data.RssSourceRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable


class SourceListPresenter(private val view: SourceListView) {
    private val sourceRepo = RssSourceRepository()
    private val disposables = CompositeDisposable()

    fun requestData() {
        sourceRepo.getSources()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(view::showData)
            .addTo(disposables)
    }

    fun stop() {
        disposables.clear()
    }
}
