package ntktest.example.com.ntktest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onResume() {
        super.onResume();
        Retrofit r = new Retrofit.Builder()
                .baseUrl("https://meduza.io/rss/all/")
                .build();

        RssService s = r.create(RssService.class);
        Call<ResponseBody> data = s.getData("https://meduza.io/rss/all/");
        data.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    Log.e(TAG, "onResponse: length = " + response.body().string().length());
                } catch (IOException e) {
                    Log.e(TAG, "onResponse: error: " + e);
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.e(TAG, "onFailure: error: " + t);
            }
        });
    }
}
