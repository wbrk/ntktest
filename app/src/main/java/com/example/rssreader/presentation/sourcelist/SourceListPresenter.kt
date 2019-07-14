package com.example.rssreader.presentation.sourcelist

import com.example.rssreader.data.repository.RssSourceRepository
import com.example.rssreader.di.RssDatabaseFactory
import com.example.rssreader.domain.entity.RssSource
import com.example.rssreader.presentation.BasePresenter
import io.reactivex.android.schedulers.AndroidSchedulers


class SourceListPresenter(private val view: SourceListView) : BasePresenter() {
    private val sourceRepo = RssSourceRepository(RssDatabaseFactory.db.sourceDao())
    private lateinit var data: List<RssSource>

    private var removedItem: RssSource? = null
    private var removedItemPosition: Int = 0

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

    fun onRemoveItem(position: Int) {
        val item = data[position]
        data = data - item
        delete(item)

        removedItem = item
        removedItemPosition = position

        view.showData(data)
        view.showMessageOnItemRemove()
    }

    fun onUndoRemove() {
        removedItem?.let {
            add(it)
            data = (data + it).sortedById()

            removedItem = null

            view.showData(data)
            view.scrollTo(removedItemPosition)
        }
    }

    fun onFabClick() {
        view.openNewSource()
    }

    private fun add(source: RssSource) {
        sourceRepo.add(source)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe()
            .clearOnDestroy()
    }

    private fun delete(source: RssSource) {
        sourceRepo.delete(source)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe()
            .clearOnDestroy()
    }

    private fun List<RssSource>.sortedById(): List<RssSource> =
        sortedBy { it.id }
}
