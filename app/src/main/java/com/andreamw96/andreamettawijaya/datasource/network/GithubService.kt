package com.andreamw96.andreamettawijaya.datasource.network

import com.andreamw96.andreamettawijaya.datasource.network.user.GithubSearchResponse
import io.reactivex.Flowable
import retrofit2.http.GET
import retrofit2.http.Query

interface GithubService {
    @GET("users")
    fun getGithubUsers(
        @Query("since") sinceId: Int,
        @Query("per_page") perPage: Int
    ): Flowable<GithubSearchResponse>

    @GET("search/users")
    fun getGithubUserWithQueryName(
        @Query("q") query: String,
        @Query("page") page: Int
    ): Flowable<GithubSearchResponse>
}