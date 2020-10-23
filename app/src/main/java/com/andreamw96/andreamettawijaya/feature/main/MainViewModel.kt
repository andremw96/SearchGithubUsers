package com.andreamw96.andreamettawijaya.feature.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.andreamw96.usecases.GetGithubUsersByNameUseCase
import javax.inject.Inject

open class MainViewModel @Inject constructor(private val getGithubUsersByNameUseCase: GetGithubUsersByNameUseCase) :
    ViewModel() {

   /* fun getUsersByName(name: String, page: Int) {
        setQuery(name)
        callUseCase(name, page) {
            errorData.postValue(it)
        }
    }

    fun getSameUserNextPage(page: Int) {
        if (_query.value != null) {
            callUseCase(_query.value!!, page) {

            }
        }
    }*/

    val _query = MutableLiveData<String>()
    val queryChanged = MutableLiveData<Boolean>()
    fun setQuery(query: String) {
        if (_query.value != query) {
            _query.value = query
            queryChanged.value = true
        }
    }

    val getData = Transformations.switchMap(_query) { query ->
        getGithubUsersByNameUseCase.execute(GetGithubUsersByNameUseCase.Params(query, 1))
    }
}