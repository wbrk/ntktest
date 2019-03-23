package com.example.rssreader.presentation.sourcelist

import com.example.rssreader.data.repository.RssSourceRepository
import com.example.rssreader.di.RssDatabaseFactory
import com.example.rssreader.presentation.BasePresenter
import io.reactivex.android.schedulers.AndroidSchedulers


class SourceListPresenter(private val view: SourceListView) : BasePresenter() {
    private val sourceRepo = RssSourceRepository(RssDatabaseFactory.db.sourceDao())

    fun requestData() {
        sourceRepo.getSources()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(view::showData)
            .clearOnDestroy()
    }
}
