package com.example.rssreader.data

import org.simpleframework.xml.Element
import org.simpleframework.xml.Root

/*
    Simple framework is suffering.
 */
@Root(strict = false, name = "item")
class RssItemModel {
    @field:Element(name = "title", required = false, data = true)
    var title: String? = ""

    @field:Element(name = "link", required = false)
    var link: String? = ""

    @field:Element(name = "pubDate", required = false)
    var pubDate: String? = ""
}
