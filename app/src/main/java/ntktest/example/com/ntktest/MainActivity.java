package ntktest.example.com.ntktest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import org.simpleframework.xml.convert.AnnotationStrategy;
import org.simpleframework.xml.core.Persister;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.simplexml.SimpleXmlConverterFactory;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.reload).setOnClickListener(v -> load());
    }

    @Override
    protected void onResume() {
        super.onResume();
        load();
    }

    private void load() {
        Retrofit r = new Retrofit.Builder()
                .baseUrl("https://example.com")
                .addConverterFactory(SimpleXmlConverterFactory.createNonStrict(new Persister(new AnnotationStrategy())))
                .build();

        String url = "https://meduza.io/rss/all/";
        RssService s = r.create(RssService.class);
        Call<RssDocument> data = s.getData(url);
        data.enqueue(new Callback<RssDocument>() {
            @Override
            public void onResponse(Call<RssDocument> call, Response<RssDocument> response) {
                RssDocument d = response.body();
                Log.e(TAG, "onResponse: " + d);
                for (RssItem i : d.getItems()) {
                    Log.e(TAG, "onResponse: " + i);
                }
            }

            @Override
            public void onFailure(Call<RssDocument> call, Throwable t) {
                Log.e(TAG, "onFailure: error: " + t);
            }
        });
    }
}
