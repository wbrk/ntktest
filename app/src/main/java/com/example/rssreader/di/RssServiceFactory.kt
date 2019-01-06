package com.example.rssreader.di

import com.example.rssreader.data.RssService

object RssServiceFactory {
    val service: RssService by lazy {
        RetrofitFactory.retrofit.create(RssService::class.java)
    }
}