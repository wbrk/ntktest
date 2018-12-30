package com.example.rssreader.presentation.feed;

import android.support.annotation.NonNull;

import com.example.rssreader.data.RssItem;

import java.util.List;

public interface FeedView {
    void showData(@NonNull List<RssItem> data);
    void showError();
}
