package com.andreamw96.andreamettawijaya.di.main

import androidx.lifecycle.ViewModel
import com.andreamw96.andreamettawijaya.di.viewmodel.ViewModelKey
import com.andreamw96.andreamettawijaya.feature.main.MainViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class MainViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel::class)
    abstract fun bindMainViewModel(viewModel: MainViewModel): ViewModel
}