package com.example.rssreader.presentation.source;

import android.support.annotation.NonNull;

import com.example.rssreader.data.RssSource;
import com.example.rssreader.data.RssSourceRepository;

public class SourcePresenter {
    @NonNull
    private SourceView view;

    @NonNull
    private RssSourceRepository sourceRepo = new RssSourceRepository();

    public SourcePresenter(@NonNull SourceView view) {
        this.view = view;
    }

    @NonNull
    public RssSource getById(int id) {
        return sourceRepo.getById(id);
    }

    public void add(@NonNull RssSource source) {
        sourceRepo.add(source);
    }

    public void update(@NonNull RssSource source) {
        sourceRepo.update(source);
    }

    public void delete(@NonNull RssSource source) {
        sourceRepo.delete(source);
    }
}
