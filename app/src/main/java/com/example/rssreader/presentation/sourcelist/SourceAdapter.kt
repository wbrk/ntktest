package com.example.rssreader.presentation.sourcelist

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.rssreader.R
import com.example.rssreader.data.RssSource
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_source.*

class SourceAdapter : RecyclerView.Adapter<SourceAdapter.ViewHolder>() {

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
    var data: List<RssSource> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.item_source, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val source = data[position]
        holder.name.text = source.name
        holder.url.text = source.url
    }

    override fun getItemCount(): Int = data.size
}