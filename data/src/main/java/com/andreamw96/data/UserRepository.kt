package com.andreamw96.data

import com.andreamw96.domain.UserGithub

class UserRepository (private val userGithubDataSource: UserGithubDataSource) {
    fun getGithubUsers(sinceId: Int, perPage: Int): ApiResponse<List<UserGithub>> {
        return userGithubDataSource.getGithubUsers(sinceId, perPage)
    }

    fun getGithubUsersByName(name: String, page: Int): ApiResponse<List<UserGithub>> {
        return userGithubDataSource.getGithubUsersByName(name, page)
    }
}