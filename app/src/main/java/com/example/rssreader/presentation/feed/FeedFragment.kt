package com.example.rssreader.presentation.feed

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import android.view.*
import com.example.rssreader.R
import com.example.rssreader.data.RssItem
import kotlinx.android.synthetic.main.fragment_feed.*
import java.lang.Exception

class FeedFragment : Fragment(), FeedView {

    private val adapter = FeedAdapter()
    private lateinit var presenter: FeedPresenter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
        inflater.inflate(R.layout.fragment_feed, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        adapter.onItemClickListener = this::openDetails

        list.adapter = adapter
        list.setHasFixedSize(true)
        list.addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))

        swipeLayout.setOnRefreshListener(presenter::requestData)
        swipeLayout.isRefreshing = true

        presenter.requestData()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        presenter = FeedPresenter(this)

        setHasOptionsMenu(true)
    }

    override fun onStop() {
        super.onStop()
        presenter.stop()
        swipeLayout.isRefreshing = false
        // fixme re-request data if was stopped before data arrived
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) =
        inflater.inflate(R.menu.menu_feed, menu)

    override fun showData(data: List<RssItem>) {
        adapter.data = data
        adapter.notifyDataSetChanged()

        swipeLayout.isRefreshing = false
    }

    override fun showError() {
        Snackbar.make(feedRoot, "Error", Snackbar.LENGTH_SHORT).show()
        swipeLayout.isRefreshing = false
    }

    private fun openDetails(position: Int) {
        try {
            val url = adapter.data[position].link
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
            startActivity(intent)
        } catch (e: Exception) {
            Snackbar.make(feedRoot, "Couldn't open item", Snackbar.LENGTH_SHORT).show()
        }
    }
}