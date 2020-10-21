package com.andreamw96.andreamettawijaya.feature.main.datasource

import com.andreamw96.andreamettawijaya.datasource.network.GithubService
import com.andreamw96.andreamettawijaya.utils.toDomainModel
import com.andreamw96.data.ApiResponse
import com.andreamw96.data.UserGithubDataSource
import com.andreamw96.domain.UserGithub
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class SearchGithubUsersDataSource @Inject constructor(
    private val githubService: GithubService,
    private val compositeDisposable: CompositeDisposable
) : UserGithubDataSource {

    private var listUsers = ApiResponse<List<UserGithub>>(ApiResponse.StatusResponse.ERROR, null, null)

    override fun getGithubUsers(sinceId: Int, perPage: Int): ApiResponse<List<UserGithub>> {
        compositeDisposable.add(
            githubService
                .getGithubUsers(sinceId, perPage)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    listUsers = if (it.items.isNotEmpty()) {
                        val result = it.items.map { userResponse ->
                            userResponse.toDomainModel()
                        }
                        ApiResponse.success(result)
                    } else {
                        ApiResponse.empty("No Data", null)
                    }
                }, {
                    listUsers = ApiResponse.error("${it.stackTrace}", null)
                })
        )

        return listUsers
    }

    override fun getGithubUsersByName(name: String, page: Int): ApiResponse<List<UserGithub>> {
        compositeDisposable.add(
            githubService
                .getGithubUserWithQueryName(name, page)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    if (it.items.isNotEmpty()) {
                        val result = it.items.map { userResponse ->
                            userResponse.toDomainModel()
                        }
                        listUsers = ApiResponse.success(result)
                    } else {
                        listUsers = ApiResponse.empty("No Data", null)
                    }
                }, {
                    listUsers = ApiResponse.error("${it.stackTrace}", null)
                })
        )

        return listUsers
    }
}