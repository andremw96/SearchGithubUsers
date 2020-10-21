package com.andreamw96.andreamettawijaya.di

import com.andreamw96.andreamettawijaya.feature.main.datasource.SearchGithubUsersDataSource
import com.andreamw96.data.UserRepository
import com.andreamw96.usecases.GetGithubUsersByNameUseCase
import dagger.Module
import dagger.Provides

@Module
class UseCaseModule {
    @Provides
    fun provideUserRepository(searchGithubUsersDataSource: SearchGithubUsersDataSource): UserRepository {
        return UserRepository(searchGithubUsersDataSource)
    }

    @Provides
    fun provideGetGithubUsersByNameUseCase(userRepository: UserRepository): GetGithubUsersByNameUseCase {
        return GetGithubUsersByNameUseCase(userRepository)
    }
}