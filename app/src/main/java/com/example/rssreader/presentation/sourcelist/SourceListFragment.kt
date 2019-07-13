package com.example.rssreader.presentation.sourcelist

import android.os.Bundle
import androidx.recyclerview.widget.DividerItemDecoration
import android.view.View
import androidx.navigation.findNavController
import com.example.rssreader.BaseFragment
import com.example.rssreader.R
import com.example.rssreader.domain.entity.RssSource
import kotlinx.android.synthetic.main.fragment_source_list.*

class SourceListFragment : BaseFragment(), SourceListView {

    private val adapter = SourceAdapter()
    private val presenter = SourceListPresenter(this)

    override val layout: Int = R.layout.fragment_source_list

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        adapter.onItemClickListener = presenter::onItemClick

        list.adapter = adapter
        list.setHasFixedSize(true)
        list.addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))

        fab.setOnClickListener { presenter.onFabClick() }

        setTitle(R.string.sources_screen)
        setNavigationIcon(R.drawable.ic_arrow_back_24dp)

        presenter.start()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        presenter.stop()
    }

    override fun showData(data: List<RssSource>) {
        adapter.data = data
    }

    override fun openEditSource(sourceId: Int) {
        val action = SourceListFragmentDirections.actionEditSource(sourceId)
        navController.navigate(action)
    }

    override fun openNewSource() {
        navController.navigate(R.id.actionEditSource)
    }
}