package ntktest.example.com.ntktest.presentation;

import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import ntktest.example.com.ntktest.R;
import ntktest.example.com.ntktest.presentation.feed.FeedFragment;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";

    private static final String TAG_FEED = "feed";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FragmentManager fm = getSupportFragmentManager();
        if (fm.findFragmentByTag(TAG_FEED) == null) {
            fm.beginTransaction()
                    .add(R.id.activity_root, new FeedFragment(), TAG_FEED)
                    .commit();
        }
    }
}
