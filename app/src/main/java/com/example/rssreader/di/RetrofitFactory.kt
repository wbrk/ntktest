package com.example.rssreader.di

import org.simpleframework.xml.convert.AnnotationStrategy
import org.simpleframework.xml.core.Persister
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.simplexml.SimpleXmlConverterFactory

object RetrofitFactory {
    val retrofit: Retrofit by lazy {
        val factory = SimpleXmlConverterFactory.createNonStrict(Persister(AnnotationStrategy()))
        Retrofit.Builder()
            .baseUrl("https://example.com")
            .client(OkHttpClientFactory.client)
            .addConverterFactory(factory)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
    }
}