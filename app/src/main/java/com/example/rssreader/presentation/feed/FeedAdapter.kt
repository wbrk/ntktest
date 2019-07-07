package com.example.rssreader.presentation.feed

import android.view.View
import android.view.ViewGroup
import com.example.rssreader.R
import com.example.rssreader.domain.entity.RssItem
import com.example.rssreader.presentation.BaseAdapter
import com.example.rssreader.presentation.BaseViewHolder
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_feed.*

class FeedAdapter : BaseAdapter<FeedAdapter.ViewHolder>() {

    inner class ViewHolder(override val containerView: View, clickListener: (Int) -> Unit)
        : BaseViewHolder(containerView, clickListener), LayoutContainer

    var data: List<RssItem> = emptyList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = inflate(R.layout.item_feed, parent)
        return ViewHolder(view, onItemClickListener)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = data[position]

        holder.title.text = item.title
        holder.date.text = item.pubDate
        holder.source.text = "Meduza"
    }

    override fun getItemCount(): Int = data.size
}