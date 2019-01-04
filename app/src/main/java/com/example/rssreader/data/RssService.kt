package com.example.rssreader.data

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Url

interface RssService {
    @GET
    fun getData(@Url url: String): Call<RssDocument>
}
