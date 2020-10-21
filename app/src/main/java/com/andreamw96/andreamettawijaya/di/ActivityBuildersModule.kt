package com.andreamw96.andreamettawijaya.di

import com.andreamw96.andreamettawijaya.feature.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuildersModule {

    @ContributesAndroidInjector()
    abstract fun contributeMainActivity(): MainActivity

}