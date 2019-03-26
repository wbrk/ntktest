package com.example.rssreader.presentation.sourcelist

import android.view.View
import android.view.ViewGroup
import com.example.rssreader.R
import com.example.rssreader.domain.entity.RssSource
import com.example.rssreader.presentation.BaseAdapter
import com.example.rssreader.presentation.BaseViewHolder
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_source.*

class SourceAdapter : BaseAdapter<SourceAdapter.ViewHolder>() {

    inner class ViewHolder(override val containerView: View, clickListener: (Int) -> Unit)
        : BaseViewHolder(containerView, clickListener), LayoutContainer

    var data: List<RssSource> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = inflate(R.layout.item_source, parent)
        return ViewHolder(view, onItemClickListener)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val source = data[position]
        holder.name.text = source.name
        holder.url.text = source.url
    }

    override fun getItemCount(): Int = data.size
}