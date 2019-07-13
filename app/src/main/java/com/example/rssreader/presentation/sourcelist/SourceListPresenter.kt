package com.example.rssreader.presentation.sourcelist

import com.example.rssreader.data.repository.RssSourceRepository
import com.example.rssreader.di.RssDatabaseFactory
import com.example.rssreader.domain.entity.RssSource
import com.example.rssreader.presentation.BasePresenter
import io.reactivex.android.schedulers.AndroidSchedulers


class SourceListPresenter(private val view: SourceListView) : BasePresenter() {
    private val sourceRepo = RssSourceRepository(RssDatabaseFactory.db.sourceDao())
    private lateinit var data: List<RssSource>

    override fun start() {
        sourceRepo.sources
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSuccess { data = it }
            .subscribe(view::showData)
            .clearOnDestroy()
    }

    fun onItemClick(position: Int) {
        view.openEditSource(data[position].id)
    }

    fun onFabClick() {
        view.openNewSource()
    }
}
