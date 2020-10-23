package com.andreamw96.andreamettawijaya.feature.main

import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.andreamw96.usecases.GetGithubUsersByNameUseCase
import javax.inject.Inject

open class MainViewModel @Inject constructor(private val getGithubUsersByNameUseCase: GetGithubUsersByNameUseCase) :
    ViewModel() {

    val _query = MutableLiveData<String>()
    val _page = MutableLiveData<Int>()
    val queryChanged = MutableLiveData<Boolean>()

    fun setQueryChanged() {
        queryChanged.value = true
    }

    fun setQuery(query: String) {
        if (_query.value != query) {
            _query.value = query
        }
    }

    fun setPage(page: Int) {
        if (_page.value != page) {
            _page.value = page
        }
    }


    val input = MediatorLiveData<Pair<String?, Int?>>().apply {
        addSource(_query) {
            value = Pair(it, _page.value)
        }

        addSource(_page) {
            value = Pair(_query.value, it)
        }
    }

    val searchGithubUsers = Transformations.switchMap(input) { input ->
        val query = input.first
        val page = input.second
        if (query != null && page != null) {
            getGithubUsersByNameUseCase.execute(GetGithubUsersByNameUseCase.Params(query, page))
        } else {
            null
        }
    }

    fun retry() {
        _query.value = _query.value
        _page.value = _page.value
    }

    fun retryplus() {
        _query.value = _query.value
        _page.value = _page.value?.plus(1)
    }
}