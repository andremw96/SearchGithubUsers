package com.andreamw96.andreamettawijaya.di

import com.andreamw96.andreamettawijaya.datasource.network.GithubService
import dagger.Module
import dagger.Provides
import io.reactivex.disposables.CompositeDisposable
import retrofit2.Retrofit

@Module
class DataModule {

    @Provides
    fun provideGitHubService(retrofit: Retrofit): GithubService {
        return retrofit.create(GithubService::class.java)
    }

    @Provides
    fun provideComposite(): CompositeDisposable {
        return CompositeDisposable()
    }
}