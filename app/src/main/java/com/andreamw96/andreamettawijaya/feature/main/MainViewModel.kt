package com.andreamw96.andreamettawijaya.feature.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.andreamw96.andreamettawijaya.datasource.network.user.GithubUserResponse
import com.andreamw96.andreamettawijaya.utils.toPresentationModel
import com.andreamw96.usecases.GetGithubUsersByNameUseCase
import javax.inject.Inject

open class MainViewModel @Inject constructor(private val getGithubUsersByNameUseCase: GetGithubUsersByNameUseCase) :
    ViewModel() {

    val isLoading = MutableLiveData<Boolean>()
    val userData = MutableLiveData<List<GithubUserResponse>>()
    val errorData = MutableLiveData<String>()

    fun getUsersByName(name: String, page: Int) {
        isLoading.postValue(true)
        getGithubUsersByNameUseCase.execute({
            userData.postValue(it.map {userDomain->
                userDomain.toPresentationModel()
            })
        }, {
            errorData.postValue(it.message)
        }, {
            isLoading.postValue(false)
        }, GetGithubUsersByNameUseCase.Params(name, page))
    }
}