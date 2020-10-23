package com.andreamw96.data

import com.andreamw96.domain.UserGithub
import io.reactivex.Flowable

class UserRepository (private val userGithubDataSource: UserGithubDataSource) {
    fun getGithubUsersByName(name: String, page: Int): Flowable<List<UserGithub>> {
        return userGithubDataSource.getGithubUsersByName(name, page)
    }
}