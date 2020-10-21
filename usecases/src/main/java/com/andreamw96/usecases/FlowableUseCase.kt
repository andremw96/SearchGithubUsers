package com.andreamw96.usecases

import io.reactivex.Flowable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

abstract class FlowableUseCase<T, in Params> : UseCase() {

    internal abstract fun buildUseCaseFlowable(params: Params): Flowable<T>

    fun execute(
        onSuccess: ((t: T) -> Unit),
        onError: ((t: Throwable) -> Unit),
        onFinished: () -> Unit = {},
        params: Params
    ) {
        disposeLast()
        lastDisposable = buildUseCaseFlowable(params)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doAfterTerminate(onFinished)
            .subscribe(onSuccess, onError)
        lastDisposable.let {
            if (it != null) {
                compositeDisposable.add(it)
            }
        }
    }
}