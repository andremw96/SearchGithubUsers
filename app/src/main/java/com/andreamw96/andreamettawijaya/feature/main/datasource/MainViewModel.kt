package com.andreamw96.andreamettawijaya.feature.main.datasource

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.andreamw96.andreamettawijaya.datasource.network.user.GithubUserResponse
import com.andreamw96.andreamettawijaya.utils.toPresentationModel
import com.andreamw96.usecases.GetGithubUsersByNameUseCase
import javax.inject.Inject

open class MainViewModel @Inject constructor(private val getGithubUsersByNameUseCase: GetGithubUsersByNameUseCase) :
    ViewModel() {
    private var _page = MutableLiveData<Int>()

    fun setPage(page: Int) {
        if (_page.value != page) {
            _page.value = page
        }
    }

    val userData = MutableLiveData<List<GithubUserResponse>>()

    fun getUsersByName() {
        getGithubUsersByNameUseCase.execute({
            userData.postValue(it.map {userDomain->
                userDomain.toPresentationModel()
            })
        }, {

        }, {

        }, GetGithubUsersByNameUseCase.Params("pikachu", 1))
    }
}