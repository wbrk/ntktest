package com.example.rssreader.presentation.feed

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import androidx.recyclerview.widget.DividerItemDecoration
import android.view.*
import com.example.rssreader.BaseFragment
import com.example.rssreader.R
import com.example.rssreader.domain.entity.RssItem
import kotlinx.android.synthetic.main.fragment_feed.*
import java.lang.Exception

class FeedFragment : BaseFragment(), FeedView {
    private val adapter = FeedAdapter()
    private val presenter = FeedPresenter(this)

    override val layout: Int = R.layout.fragment_feed

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        adapter.onItemClickListener = presenter::onItemSelected

        list.adapter = adapter
        list.setHasFixedSize(true)
        list.addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))

        swipeLayout.setOnRefreshListener(presenter::onRefresh)

        setTitle(R.string.feed_screen)

        presenter.start()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        presenter.stop()
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_feed, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.actionShowSourceList) {
            // todo should presenter do routing instead?
            navController.navigate(item.itemId)
            return true
        }
        return false
    }

    override fun showData(data: List<RssItem>) {
        adapter.data = data
    }

    override fun showError() {
        Snackbar.make(feedRoot, "Error", Snackbar.LENGTH_SHORT).show()
    }

    override fun showProgress() {
        swipeLayout.isRefreshing = true
    }

    override fun hideProgress() {
        swipeLayout.isRefreshing = false
    }

    override fun openDetails(url: String) {
        try {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
            startActivity(intent)
        } catch (e: Exception) {
            Snackbar.make(feedRoot, "Couldn't open item", Snackbar.LENGTH_SHORT).show()
        }
    }
}