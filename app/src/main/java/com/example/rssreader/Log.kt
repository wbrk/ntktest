package com.example.rssreader

import timber.log.Timber

// region error
inline fun logError(t: Throwable) = Timber.e(t)

inline fun logError(message: String, vararg args: Any) = Timber.e(message, args)

inline fun logError(t: Throwable, message: String, vararg args: Any) = Timber.e(t, message, args)
// endregion error

// region info
inline fun logInfo(t: Throwable) = Timber.e(t)

inline fun logInfo(message: String, vararg args: Any) = Timber.e(message, args)

inline fun logInfo(t: Throwable, message: String, vararg args: Any) = Timber.e(t, message, args)
// endregion info
