package com.example.rssreader.data

import android.net.Uri
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import okhttp3.OkHttpClient
import org.simpleframework.xml.convert.AnnotationStrategy
import org.simpleframework.xml.core.Persister
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.simplexml.SimpleXmlConverterFactory
import java.util.concurrent.TimeUnit

class DataRetriever(private val uri: Uri) {
    private val service: RssService

    init {
        val client = OkHttpClient.Builder()
            .connectTimeout(5, TimeUnit.SECONDS)
            .readTimeout(5, TimeUnit.SECONDS)
            .build()

        val factory = SimpleXmlConverterFactory.createNonStrict(Persister(AnnotationStrategy()))

        val retrofit = Retrofit.Builder()
            .baseUrl("https://example.com")
            .client(client)
            .addConverterFactory(factory)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()

        service = retrofit.create(RssService::class.java)
    }

    fun load(): Single<List<RssItem>> =
        service.getData(uri.toString())
            .subscribeOn(Schedulers.io())
            .map { it.items }
}