package com.example.rssreader.presentation.feed;

import android.net.Uri;
import android.support.annotation.NonNull;
import android.util.Log;

import com.example.rssreader.data.DataRetriever;

import kotlin.Unit;

public class FeedPresenter {
    private static final String TAG = "FeedPresenter";

    @NonNull private DataRetriever retriever;
    @NonNull private FeedView view;

    public FeedPresenter(@NonNull FeedView view) {
        this.view = view;
        retriever = new DataRetriever(Uri.parse("https://meduza.io/rss/all/"),
                (data) -> {
                    view.showData(data);
                    return Unit.INSTANCE;
                },
                (code, t) -> {
                    Log.i(TAG, "onError: code: " + code);
                    Log.i(TAG, "onError: error: " + t);
                    view.showError();

                    return Unit.INSTANCE;
                }) ;
    }

    public void requestData() {
        retriever.load();
    }

    public void stop() {
        retriever.cancel();
    }
}
