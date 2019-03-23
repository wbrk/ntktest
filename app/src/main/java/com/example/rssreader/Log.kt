package com.example.rssreader

import timber.log.Timber

// region error
fun logError(t: Throwable) = Timber.e(t)

fun logError(message: String, vararg args: Any) = Timber.e(message, args)

fun logError(t: Throwable, message: String, vararg args: Any) = Timber.e(t, message, args)
// endregion error

// region info
fun logInfo(t: Throwable) = Timber.e(t)

fun logInfo(message: String, vararg args: Any) = Timber.e(message, args)

fun logInfo(t: Throwable, message: String, vararg args: Any) = Timber.e(t, message, args)
// endregion info
