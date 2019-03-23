package com.example.rssreader.data.service

import com.example.rssreader.data.model.RssDocument
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Url

interface RssService {
    @GET
    fun getData(@Url url: String): Single<RssDocument>
}
