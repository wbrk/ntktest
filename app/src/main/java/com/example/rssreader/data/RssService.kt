package com.example.rssreader.data

import io.reactivex.Single
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Url

interface RssService {
    @GET
    fun getData(@Url url: String): Single<RssDocument>
}
