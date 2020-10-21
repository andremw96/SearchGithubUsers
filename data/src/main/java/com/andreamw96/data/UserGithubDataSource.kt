package com.andreamw96.data

import com.andreamw96.domain.UserGithub
import io.reactivex.Flowable

interface UserGithubDataSource {
    fun getGithubUsers(sinceId: Int, perPage: Int): Flowable<List<UserGithub>>
    fun getGithubUsersByName(name: String, page: Int): Flowable<List<UserGithub>>
}