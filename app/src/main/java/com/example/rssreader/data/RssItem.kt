package com.example.rssreader.data

import org.simpleframework.xml.Element
import org.simpleframework.xml.Root

// todo figure out that 'required' thing (look at RssDocument)
@Root(strict = false, name = "item")
data class RssItem(
    @param:Element(name = "title", required = false, data = true)
    @field:Element(name = "title", required = false, data = true)
    val title: String = "",

    @param:Element(name = "link", required = false)
    @field:Element(name = "link", required = false)
    val link: String = "",

    @param:Element(name = "pubDate", required = false)
    @field:Element(name = "pubDate", required = false)
    val pubDate: String = ""
)
