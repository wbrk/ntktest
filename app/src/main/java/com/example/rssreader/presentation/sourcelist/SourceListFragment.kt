package com.example.rssreader.presentation.sourcelist

import android.os.Bundle
import androidx.recyclerview.widget.DividerItemDecoration
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.rssreader.presentation.BaseFragment
import com.example.rssreader.R
import com.example.rssreader.domain.entity.RssSource
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.fragment_source_list.*

class SourceListFragment : BaseFragment(), SourceListView {

    private val adapter = SourceAdapter()
    private val presenter = SourceListPresenter(this)
    private var snackbar: Snackbar? = null

    override val layout: Int = R.layout.fragment_source_list

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        adapter.onItemClickListener = presenter::onItemClick
        adapter.onRemoveClickListener = presenter::onRemoveItem

        list.adapter = adapter
        list.setHasFixedSize(true)
        list.addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))
        list.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                snackbar?.dismiss()
            }
        })

        fab.setOnClickListener { presenter.onFabClick() }

        setTitle(R.string.sources_screen)
        setNavigationIcon(R.drawable.ic_arrow_back_24dp)

        presenter.start()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        presenter.stop()
        snackbar = null
    }

    override fun showData(data: List<RssSource>) {
        adapter.data = data
    }

    override fun showMessageOnItemRemove() {
        snackbar = Snackbar
            .make(sourceListRoot, R.string.source_has_been_removed, Snackbar.LENGTH_INDEFINITE)
            .setAction(R.string.undo) { presenter.onUndoRemove() }

        snackbar?.show()
    }

    override fun scrollTo(position: Int) {
        list.scrollToPosition(position)
    }

    override fun openEditSource(sourceId: Int) {
        val action = SourceListFragmentDirections.actionEditSource(sourceId)
        navController.navigate(action)
    }

    override fun openNewSource() {
        navController.navigate(R.id.actionEditSource)
    }
}