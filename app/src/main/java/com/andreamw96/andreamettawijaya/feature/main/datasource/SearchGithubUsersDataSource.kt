package com.andreamw96.andreamettawijaya.feature.main.datasource

import com.andreamw96.andreamettawijaya.datasource.network.GithubService
import com.andreamw96.andreamettawijaya.utils.toDomainModel
import com.andreamw96.data.UserGithubDataSource
import com.andreamw96.domain.UserGithub
import io.reactivex.Flowable
import javax.inject.Inject

class SearchGithubUsersDataSource @Inject constructor(
    private val githubService: GithubService
) : UserGithubDataSource {

    override fun getGithubUsers(
        sinceId: Int,
        perPage: Int
    ): Flowable<List<UserGithub>> {
        return githubService.getGithubUsers(sinceId, perPage).map {
            it.items.map { userResp ->
                userResp.toDomainModel()
            }
        }
    }

    override fun getGithubUsersByName(
        name: String,
        page: Int
    ): Flowable<List<UserGithub>> {
        return githubService.getGithubUserWithQueryName(name, page).map {
            it.items.map { userResp ->
                userResp.toDomainModel()
            }
        }
    }
}