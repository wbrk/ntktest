package com.example.rssreader.presentation

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.recyclerview.widget.RecyclerView

abstract class BaseAdapter<VH : RecyclerView.ViewHolder> : RecyclerView.Adapter<VH>() {
    var onItemClickListener: (position: Int) -> Unit = {}

    fun inflate(@LayoutRes layout: Int, root: ViewGroup): View =
        LayoutInflater.from(root.context)
            .inflate(layout, root, false)
}

abstract class BaseViewHolder(itemView: View, clickListener: (position: Int) -> Unit)
    : RecyclerView.ViewHolder(itemView) {

    init {
        itemView.setOnClickListener {
            if (adapterPosition != RecyclerView.NO_POSITION) {
                clickListener(adapterPosition)
            }
        }
    }
}