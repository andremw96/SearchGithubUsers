package com.andreamw96.data

import com.andreamw96.domain.UserGithub
import io.reactivex.Flowable

interface UserGithubDataSource {
    fun getGithubUsersByName(name: String, page: Int): Flowable<List<UserGithub>>
}