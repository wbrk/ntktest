package com.example.rssreader.presentation.sourcelist

import android.os.Bundle
import androidx.recyclerview.widget.DividerItemDecoration
import android.view.View
import androidx.navigation.Navigation
import androidx.navigation.fragment.NavHostFragment
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
            val action = SourceListFragmentDirections.actionEditSource()
            action.sourceId = adapter.data[position].id
            NavHostFragment.findNavController(this).navigate(action)
        }

        list.adapter = adapter
        list.setHasFixedSize(true)
        list.addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))

        fab.setOnClickListener { v ->
            Navigation.findNavController(v).navigate(R.id.actionEditSource)
        }
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