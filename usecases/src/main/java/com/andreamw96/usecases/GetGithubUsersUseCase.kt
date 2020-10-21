package com.andreamw96.usecases

import com.andreamw96.data.UserRepository
import com.andreamw96.domain.UserGithub

class GetGithubUsersUseCase(private val userRepository: UserRepository) {
    operator fun invoke(sinceId: Int, perPage: Int) = userRepository.getGithubUsers(sinceId, perPage)
}