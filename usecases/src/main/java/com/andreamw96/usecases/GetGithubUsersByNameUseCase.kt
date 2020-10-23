package com.andreamw96.usecases

import com.andreamw96.data.UserRepository
import com.andreamw96.domain.UserGithub
import com.andreamw96.usecases.base.FlowableUseCase
import io.reactivex.Flowable

class GetGithubUsersByNameUseCase(private val userRepository: UserRepository) :
    FlowableUseCase<List<UserGithub>, GetGithubUsersByNameUseCase.Params>() {

    class Params(
        val name: String,
        val page: Int
    )

    override fun buildUseCaseFlowable(params: Params): Flowable<List<UserGithub>> {
        return userRepository.getGithubUsersByName(
            params.name,
            params.page
        )
    }
}