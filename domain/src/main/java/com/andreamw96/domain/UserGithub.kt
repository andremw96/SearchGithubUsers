package com.andreamw96.domain


data class UserGithub(
    val avatarUrl: String,
    val eventsUrl: String,
    val followersUrl: String,
    val followingUrl: String,
    val gistsUrl: String,
    val gravatarId: String,
    val htmlUrl: String,
    val id: Int,
    val login: String,
    val nodeId: String,
    val organizationsUrl: String,
    val receivedEventsUrl: String,
    val reposUrl: String,
    val score: Int,
    val siteAdmin: Boolean,
    val starredUrl: String,
    val subscriptionsUrl: String,
    val type: String,
    val url: String
)