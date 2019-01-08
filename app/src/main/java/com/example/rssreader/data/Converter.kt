package com.example.rssreader.data

interface Converter<T, R> {
    fun convert(value: T): R
}
