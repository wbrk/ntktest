package com.example.rssreader.data

import android.net.Uri
import okhttp3.OkHttpClient
import org.simpleframework.xml.convert.AnnotationStrategy
import org.simpleframework.xml.core.Persister
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Retrofit
import retrofit2.converter.simplexml.SimpleXmlConverterFactory
import java.util.concurrent.TimeUnit

class DataRetriever(
    private val uri: Uri,
    private val onSuccess: (data: List<RssItem>) -> Unit,
    private val onError: (responseCode: Int, t: Throwable?) -> Unit
) {

    private val service: RssService
    private var call: Call<RssDocument>? = null

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
            .build()

        service = retrofit.create(RssService::class.java)
    }

    fun load() {
        if (call != null) {
            return
        }

        val apiCall = service.getData(uri.toString())
        apiCall.enqueue(object : Callback<RssDocument> {
            override fun onResponse(call: Call<RssDocument>, response: retrofit2.Response<RssDocument>) {
                this@DataRetriever.call = null
                if (response.isSuccessful) {
                    onSuccess(response.body()!!.items)
                } else {
                    onError(response.code(), null)
                }
            }

            override fun onFailure(call: Call<RssDocument>, t: Throwable) {
                this@DataRetriever.call = null
                if (!call.isCanceled) {
                    onError(-1, t)
                }
            }
        })

        this.call = apiCall
    }

    fun cancel() {
        call?.cancel()
        call = null
    }
}