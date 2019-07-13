package com.example.rssreader.presentation

import com.example.rssreader.extensions.addTo
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

abstract class BasePresenter {
    private val disposables = CompositeDisposable()

    open fun start() = Unit

    open fun stop() {
        disposables.clear()
    }

    protected fun Disposable.clearOnDestroy() = addTo(disposables)
}