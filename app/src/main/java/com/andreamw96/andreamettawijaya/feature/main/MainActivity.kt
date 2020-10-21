package com.andreamw96.andreamettawijaya.feature.main

import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.andreamw96.andreamettawijaya.R
import com.andreamw96.andreamettawijaya.di.viewmodel.ViewModelProvidersFactory
import com.andreamw96.andreamettawijaya.feature.main.datasource.MainViewModel
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

class MainActivity : DaggerAppCompatActivity() {

    @Inject
    lateinit var viewModelProviderFactory: ViewModelProvidersFactory

    private lateinit var mainViewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mainViewModel = ViewModelProvider(this, viewModelProviderFactory).get(MainViewModel::class.java)
        mainViewModel.getUsersByName()
        mainViewModel.userData.observe(this, Observer {
            for (element in it) {
                Log.d("response", element.login)
            }
        })
    }
}