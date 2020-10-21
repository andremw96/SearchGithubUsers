package com.andreamw96.usecases

import com.andreamw96.data.UserRepository
import com.andreamw96.domain.UserGithub

class GetGithubUsersByNameUseCase(private val userRepository: UserRepository) {
    operator fun invoke(name: String, page: Int) = userRepository.getGithubUsersByName(name, page)
}