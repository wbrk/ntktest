package com.example.rssreader.data

import org.simpleframework.xml.ElementList
import org.simpleframework.xml.Path
import org.simpleframework.xml.Root

@Root(strict = false, name = "rss")
data class RssDocument(
    /**
     * Empty if items are absent from xml document.
     */
    @field:Path("channel")
    @field:ElementList(name = "items", required = false, inline = true)
    @param:ElementList(name = "items", required = false, inline = true)
    val items: List<RssItemModel> = emptyList()
)