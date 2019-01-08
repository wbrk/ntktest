package com.example.rssreader.data

object RssItemConverter : Converter<RssItemModel, RssItem> {
    override fun convert(value: RssItemModel): RssItem =
        RssItem(
            title = value.title.orEmpty(),
            link = value.link.orEmpty(),
            pubDate = value.pubDate.orEmpty()
        )
}