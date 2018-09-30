package ntktest.example.com.ntktest.presentation;

import java.util.List;

import ntktest.example.com.ntktest.data.RssItem;

public interface FeedView {
    void showData(List<RssItem> data);
    void showError();
}
