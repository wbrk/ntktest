package ntktest.example.com.ntktest.presentation;

import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import ntktest.example.com.ntktest.R;
import ntktest.example.com.ntktest.presentation.feed.FeedFragment;
import ntktest.example.com.ntktest.presentation.source.SourceFragment;

public class MainActivity extends AppCompatActivity implements Router {
    private static final String TAG = "MainActivity";

    private static final String TAG_FEED = "feed";
    private static final String TAG_SOURCE = "source";

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

    @Override
    protected void onResume() {
        super.onResume();
        RouterHolder.setRouter(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        RouterHolder.setRouter(null);
    }

    @Override
    public void showSources() {
        FragmentManager fm = getSupportFragmentManager();
        SourceFragment source = (SourceFragment) fm.findFragmentByTag(TAG_SOURCE);
        if (source == null) {
            source = new SourceFragment();
        }

        fm.beginTransaction()
                .replace(R.id.activity_root, source, TAG_SOURCE)
                .addToBackStack(null)
                .commit();
    }
}
