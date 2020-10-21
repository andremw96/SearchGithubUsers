package com.andreamw96.data

import com.andreamw96.domain.UserGithub

interface UserGithubDataSource {
    fun getGithubUsers(sinceId: Int, perPage: Int): List<UserGithub>
    fun getGithubUsersByName(name: String, page: Int): List<UserGithub>
}