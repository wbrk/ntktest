package ntktest.example.com.ntktest.data;

import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import org.simpleframework.xml.convert.AnnotationStrategy;
import org.simpleframework.xml.core.Persister;

import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Converter;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.simplexml.SimpleXmlConverterFactory;

public class DataRetriever {
    private static final String TAG = "DataRetriever";

    public interface OnSuccess {
        void onSuccess(@NonNull List<RssItem> data);
    }

    public interface OnError {
        void onError(int responseCode, @Nullable Throwable t);
    }

    public static class Builder {
        @NonNull
        private DataRetriever retriever = new DataRetriever();

        @NonNull
        public Builder from(@NonNull Uri uri) {
            retriever.setUri(uri);
            return this;
        }

        @NonNull
        public Builder onSuccess(@NonNull OnSuccess listener) {
            retriever.setSuccessListener(listener);
            return this;
        }

        @NonNull
        public Builder onError(@NonNull OnError listener) {
            retriever.setErrorListener(listener);
            return this;
        }

        @NonNull
        public DataRetriever build() {
            return retriever;
        }
    }

    @NonNull private RssService service;

    private OnSuccess successListener;
    private OnError errorListener;
    private Uri uri;

    @Nullable private Call<RssDocument> call;

    public void load() {
        if (call != null) {
            return;
        }

        call = service.getData(uri.toString());
        call.enqueue(new Callback<RssDocument>() {
            @Override
            public void onResponse(Call<RssDocument> call, Response<RssDocument> response) {
                DataRetriever.this.call = null;
                if (response.isSuccessful()) {
                    successListener.onSuccess(response.body().getItems());
                } else {
                    errorListener.onError(response.code(), null);
                }
            }

            @Override
            public void onFailure(Call<RssDocument> call, Throwable t) {
                DataRetriever.this.call = null;
                errorListener.onError(-1, t);
            }
        });
    }

    public void cancel() {
        if (call != null) {
            call.cancel();
            call = null;
        }
    }

    private DataRetriever() {
        OkHttpClient client = new OkHttpClient.Builder()
                .connectTimeout(5, TimeUnit.SECONDS)
                .readTimeout(5, TimeUnit.SECONDS)
                .build();

        Converter.Factory factory
                = SimpleXmlConverterFactory.createNonStrict(new Persister(new AnnotationStrategy()));

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://example.com")
                .client(client)
                .addConverterFactory(factory)
                .build();

        service = retrofit.create(RssService.class);
    }

    private void setUri(@NonNull Uri uri) {
        this.uri = uri;
    }

    private void setSuccessListener(@NonNull OnSuccess listener) {
        successListener = listener;
    }

    private void setErrorListener(@NonNull OnError listener) {
        errorListener = listener;
    }
}
