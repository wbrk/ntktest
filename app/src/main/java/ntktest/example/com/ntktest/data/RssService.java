package ntktest.example.com.ntktest.data;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Url;

public interface RssService {
    @GET
    Call<RssDocument> getData(@Url String url);
}
