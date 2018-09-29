package ntktest.example.com.ntktest.presentation;

import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import ntktest.example.com.ntktest.data.DataRetriever;
import ntktest.example.com.ntktest.R;
import ntktest.example.com.ntktest.data.RssDocument;
import ntktest.example.com.ntktest.data.RssItem;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";

    DataRetriever retriever;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        retriever = new DataRetriever.Builder()
                .from(Uri.parse("https://meduza.io/rss/all/"))
                .onSuccess(this::onLoad)
                .onError(this::onError)
                .build();

        findViewById(R.id.reload).setOnClickListener(v -> retriever.load());
    }

    @Override
    protected void onStart() {
        super.onStart();
        retriever.load();
    }

    @Override
    protected void onStop() {
        super.onStop();
        retriever.cancel();
    }

    void onLoad(RssDocument document) {
        for (RssItem item : document.getItems()) {
            Log.e(TAG, "onLoad: " + item);
        }
    }

    void onError(int code, Throwable t) {
        if (t != null) {
            Log.e(TAG, "onError: " + t);
        } else {
            Log.e(TAG, "onError: http code " + code);
        }
    }
}
