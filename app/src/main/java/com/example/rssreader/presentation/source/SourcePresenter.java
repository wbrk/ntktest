package com.example.rssreader.presentation.source;

import android.support.annotation.NonNull;

import com.example.rssreader.data.RssSource;
import com.example.rssreader.data.RssSourceRepository;

public class SourcePresenter {
    @NonNull
    private SourceView view;

    public SourcePresenter(@NonNull SourceView view) {
        this.view = view;
    }

    @NonNull
    public RssSource getById(int id) {
        return RssSourceRepository.instance().getById(id);
    }

    public void add(@NonNull RssSource source) {
        RssSourceRepository.instance().add(source);
    }

    public void update(@NonNull RssSource source) {
        RssSourceRepository.instance().update(source);
    }

    public void delete(@NonNull RssSource source) {
        RssSourceRepository.instance().delete(source);
    }
}
