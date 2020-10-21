package com.andreamw96.andreamettawijaya.datasource.network

import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface GithubService {
    @GET("users")
    fun getGithubUsers(
        @Query("since") sinceId: Int,
        @Query("per_page") perPage: Int
    ): Observable<GithubSearchResponse>

    @GET("search/users")
    fun getGithubUserWithQueryName(
        @Query("q") query: String,
        @Query("page") page: Int
    ): Observable<GithubSearchResponse>
}