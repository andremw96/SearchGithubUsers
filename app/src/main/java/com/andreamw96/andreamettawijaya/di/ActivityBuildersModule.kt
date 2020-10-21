package com.andreamw96.andreamettawijaya.di

import com.andreamw96.andreamettawijaya.di.main.MainViewModelModule
import com.andreamw96.andreamettawijaya.feature.main.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuildersModule {

    @ContributesAndroidInjector(
        modules = [
            MainViewModelModule::class
        ]
    )
    abstract fun contributeMainActivity(): MainActivity

}