package com.andreamw96.usecases.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import io.reactivex.Flowable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

abstract class FlowableUseCase<T, in Params> : UseCase() {

    internal abstract fun buildUseCaseFlowable(params: Params): Flowable<T>

    private val listData = MutableLiveData<Resource<T>>()

    fun execute(
        params: Params
    ) : LiveData<Resource<T>> {
        disposeLast()
        listData.postValue(Resource.loading(null))
        lastDisposable = buildUseCaseFlowable(params)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                listData.postValue(Resource.success(it))
            }, {
                listData.postValue(Resource.error(it.message, null))
            })
        lastDisposable.let {
            if (it != null) {
                compositeDisposable.add(it)
            }
        }

        return listData
    }
}