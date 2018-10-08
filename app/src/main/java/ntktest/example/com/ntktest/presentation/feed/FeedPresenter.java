package ntktest.example.com.ntktest.presentation.feed;

import android.net.Uri;
import android.support.annotation.NonNull;
import android.util.Log;

import ntktest.example.com.ntktest.data.DataRetriever;

public class FeedPresenter {
    private static final String TAG = "FeedPresenter";

    @NonNull private DataRetriever retriever;
    @NonNull private FeedView view;

    public FeedPresenter(@NonNull FeedView view) {
        this.view = view;
        retriever = new DataRetriever.Builder()
                .from(Uri.parse("https://meduza.io/rss/all/"))
                .onSuccess(view::showData)
                .onError((code, t) -> {
                    Log.i(TAG, "onError: code: " + code);
                    Log.i(TAG, "onError: error: " + t);
                    view.showError();
                })
                .build();
    }

    public void requestData() {
        retriever.load();
    }

    public void stop() {
        retriever.cancel();
    }
}
