package ntktest.example.com.ntktest;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Url;

public interface RssService {
    @GET
    Call<ResponseBody> getData(@Url String url);
}
