package com.andreamw96.andreamettawijaya.datasource.network.user


import com.google.gson.annotations.SerializedName

data class GithubSearchResponse(
    @SerializedName("incomplete_results")
    val incompleteResults: Boolean,
    @SerializedName("items")
    val items: List<GithubUserResponse>,
    @SerializedName("total_count")
    val totalCount: Int
)