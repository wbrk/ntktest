package com.example.rssreader.presentation.feed

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.rssreader.R
import com.example.rssreader.domain.entity.RssItem
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_feed.*

class FeedAdapter : RecyclerView.Adapter<FeedAdapter.ViewHolder>() {

    inner class ViewHolder(override val containerView: View)
        : RecyclerView.ViewHolder(containerView), LayoutContainer {

        init {
            containerView.setOnClickListener {
                if (adapterPosition != RecyclerView.NO_POSITION) {
                    onItemClickListener(adapterPosition)
                }
            }
        }
    }

    var onItemClickListener: (position: Int) -> Unit = {}
    var data: List<RssItem> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.item_feed, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = data[position]

        holder.title.text = item.title
        holder.date.text = item.pubDate
        holder.source.text = "Meduza"
    }

    override fun getItemCount(): Int = data.size
}