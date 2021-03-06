package com.example.rssreader.presentation.sourcelist

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.rssreader.R
import com.example.rssreader.domain.entity.RssSource
import com.example.rssreader.presentation.BaseAdapter
import com.example.rssreader.presentation.BaseViewHolder
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_source.*
import kotlinx.android.synthetic.main.item_source.view.*

class SourceAdapter : BaseAdapter<SourceAdapter.ViewHolder>() {
    var onRemoveClickListener: (position: Int) -> Unit = {}

    inner class ViewHolder(
        override val containerView: View,
        itemClickListener: (Int) -> Unit,
        removeClickListener: (Int) -> Unit
    ) : BaseViewHolder(containerView, itemClickListener), LayoutContainer {

        init {
            containerView.remove.setOnClickListener {
                if (adapterPosition != RecyclerView.NO_POSITION) {
                    removeClickListener(adapterPosition)
                }
            }
        }
    }

    var data: List<RssSource> = emptyList()
        set(value) {
            val result = DiffUtil.calculateDiff(DiffCallback(field, value))
            field = value
            result.dispatchUpdatesTo(this)
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = inflate(R.layout.item_source, parent)
        return ViewHolder(view, onItemClickListener, onRemoveClickListener)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val source = data[position]
        holder.name.text = source.name
        holder.url.text = source.url
    }

    override fun getItemCount(): Int = data.size
}

class DiffCallback(
    private val old: List<RssSource>,
    private val new: List<RssSource>
) : DiffUtil.Callback() {

    override fun getOldListSize(): Int = old.size

    override fun getNewListSize(): Int = new.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
        old[oldItemPosition].id == new[newItemPosition].id

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
        old[oldItemPosition] == new[newItemPosition]
}