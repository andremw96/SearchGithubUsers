package com.andreamw96.andreamettawijaya.utils

import com.andreamw96.andreamettawijaya.datasource.network.user.GithubUserResponse
import com.andreamw96.domain.UserGithub

fun GithubUserResponse.toDomainModel() : UserGithub{
    return UserGithub(
        this.avatarUrl,
        this.eventsUrl,
        this.followersUrl,
        this.followingUrl,
        this.gistsUrl,
        this.gravatarId,
        this.htmlUrl,
        this.id,
        this.login,
        this.nodeId,
        this.organizationsUrl,
        this.receivedEventsUrl,
        this.reposUrl,
        this.score,
        this.siteAdmin,
        this.starredUrl,
        this.subscriptionsUrl,
        this.type,
        this.url
    )
}

fun UserGithub.toPresentationModel() : GithubUserResponse {
    return GithubUserResponse(
        this.avatarUrl,
        this.eventsUrl,
        this.followersUrl,
        this.followingUrl,
        this.gistsUrl,
        this.gravatarId,
        this.htmlUrl,
        this.id,
        this.login,
        this.nodeId,
        this.organizationsUrl,
        this.receivedEventsUrl,
        this.reposUrl,
        this.score,
        this.siteAdmin,
        this.starredUrl,
        this.subscriptionsUrl,
        this.type,
        this.url
    )
}

fun List<UserGithub>.toPresentationModel() : List<GithubUserResponse> {
    return this.map {
        it.toPresentationModel()
    }
}