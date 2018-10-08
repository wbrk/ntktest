package ntktest.example.com.ntktest.presentation.feed;

import android.support.annotation.NonNull;

import java.util.List;

import ntktest.example.com.ntktest.data.RssItem;

public interface FeedView {
    void showData(@NonNull List<RssItem> data);
    void showError();
}
