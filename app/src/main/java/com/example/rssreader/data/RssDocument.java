package com.example.rssreader.data;

import android.support.annotation.NonNull;

import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Path;
import org.simpleframework.xml.Root;

import java.util.ArrayList;
import java.util.List;

@Root(strict = false, name = "rss")
public class RssDocument {
    @NonNull
    @ElementList(required = false, inline = true)
    @Path("channel")
    private List<RssItem> items = new ArrayList<>(0);

    @NonNull
    public List<RssItem> getItems() {
        return items;
    }

    @NonNull
    @Override
    public String toString() {
        return "RssDocument { size=" + items.size() + " }";
    }
}
