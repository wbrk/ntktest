package com.example.rssreader.presentation.sourcelist;

import android.support.annotation.NonNull;

import com.example.rssreader.data.RssSource;
import com.example.rssreader.data.RssSourceRepository;

import java.util.List;


public class SourceListPresenter {
    @NonNull private SourceListView view;
    @NonNull private RssSourceRepository sourceRepo = new RssSourceRepository();

    public SourceListPresenter(@NonNull SourceListView view) {
        this.view = view;
    }

    public void requestData() {
        List<RssSource> data = sourceRepo.getSources();
        view.showData(data);
    }
}
