package com.example.rssreader.data

import com.example.rssreader.data.model.RssItemModel
import com.example.rssreader.domain.entity.RssItem

// todo rewrite as a function?
object RssItemConverter : Converter<RssItemModel, RssItem> {
    override fun convert(value: RssItemModel): RssItem =
        RssItem(
            title = value.title.orEmpty(),
            link = value.link.orEmpty(),
            pubDate = value.pubDate.orEmpty()
        )
}