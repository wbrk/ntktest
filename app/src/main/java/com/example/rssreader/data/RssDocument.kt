package com.example.rssreader.data

import org.simpleframework.xml.ElementList
import org.simpleframework.xml.Path
import org.simpleframework.xml.Root

// todo figure out that 'required' thing
// why it's not required?
// what will happen if it's not required and is absent in input xml?
// if it's required and is absent in xml?
@Root(strict = false, name = "rss")
data class RssDocument(
    @field:Path("channel")
    @field:ElementList(name = "items", required = false, inline = true)
    @param:ElementList(name = "items", required = false, inline = true)
    val items: List<RssItem> = emptyList()
)