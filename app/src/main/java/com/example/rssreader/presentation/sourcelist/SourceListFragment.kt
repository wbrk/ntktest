package com.example.rssreader.presentation.sourcelist

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.navigation.fragment.NavHostFragment
import com.example.rssreader.R
import com.example.rssreader.data.RssSource
import kotlinx.android.synthetic.main.fragment_source_list.*

class SourceListFragment : Fragment(), SourceListView {

    private val adapter = SourceAdapter()
    private lateinit var presenter: SourceListPresenter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
        inflater.inflate(R.layout.fragment_source_list, container, false)

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

    override fun onResume() {
        super.onResume()
        presenter.requestData()
    }

    override fun showData(data: List<RssSource>) {
        adapter.data = data
        adapter.notifyDataSetChanged()
    }
}