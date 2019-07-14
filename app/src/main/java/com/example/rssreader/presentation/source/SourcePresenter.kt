package com.example.rssreader.presentation.source

import com.example.rssreader.domain.entity.RssSource
import com.example.rssreader.data.repository.RssSourceRepository
import com.example.rssreader.di.RssDatabaseFactory
import com.example.rssreader.presentation.BasePresenter
import io.reactivex.android.schedulers.AndroidSchedulers

class SourcePresenter(
    private val sourceId: Int,
    private val view: SourceView
) : BasePresenter() {

    companion object {
        private const val NEW_SOURCE = -1
    }

    private val sourceRepo = RssSourceRepository(RssDatabaseFactory.db.sourceDao())

    private var source = RssSource()

    private val isNewSource: Boolean
        get() = (sourceId == NEW_SOURCE)

    // todo fix title flickering due to db load pause (use progressbar)
    override fun start() {
        if (isNewSource) {
            view.showNewSource()
        } else {
            sourceRepo.getById(sourceId)
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSuccess { source = it }
                .subscribe(view::showExistingSource)
                .clearOnDestroy()
        }
    }

    fun onSaveChangesClick(name: String, url: String) {
        // todo validate
        source = source.copy(
            name = name,
            url = url
        )

        if (isNewSource) {
            add(source)
        } else {
            update(source)
        }

        view.goBack()
    }

    private fun add(source: RssSource) {
        sourceRepo.add(source)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe()
            .clearOnDestroy()
    }

    private fun update(source: RssSource) {
        sourceRepo.update(source)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe()
            .clearOnDestroy()
    }
}