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
    private lateinit var presenter: SourceListPresenter

    override val layout: Int = R.layout.fragment_source_list

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        adapter.onItemClickListener = { position ->
            val sourceId = adapter.data[position].id
            val action = SourceListFragmentDirections.actionEditSource(sourceId)
            navController.navigate(action)
        }

        list.adapter = adapter
        list.setHasFixedSize(true)
        list.addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))

        fab.setOnClickListener { v ->
            v.findNavController().navigate(R.id.actionEditSource)
        }

        setTitle(R.string.sources_screen)
        setNavigationIcon(R.drawable.ic_arrow_back_24dp)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        presenter = SourceListPresenter(this)
    }

    override fun onStop() {
        super.onStop()
        presenter.stop()
    }

    override fun onResume() {
        super.onResume()
        presenter.requestData()
    }

    override fun showData(data: List<RssSource>) {
        adapter.data = data
        adapter.notifyDataSetChanged()
    }
}