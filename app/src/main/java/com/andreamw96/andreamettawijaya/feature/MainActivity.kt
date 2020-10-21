package com.andreamw96.andreamettawijaya.feature

import android.os.Bundle
import android.util.Log
import com.andreamw96.andreamettawijaya.R
import com.andreamw96.andreamettawijaya.datasource.network.GithubService
import dagger.android.support.DaggerAppCompatActivity
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class MainActivity : DaggerAppCompatActivity() {

    @Inject
    lateinit var githubService: GithubService

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        githubService.getGithubUserWithQueryName("pikachu", 1)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                for (i in 0 until it.items.size) {
                    Log.d("response $i", it.items[i].url)
                }
            }, {
                it.message?.let { it1 -> Log.d("response", it1) }
            })

        githubService.getGithubUserWithQueryName("pikachu", 2)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                for (i in 0 until it.items.size) {
                    Log.d("response $i", it.items[i].url)
                }
            }, {
                it.message?.let { it1 -> Log.d("response", it1) }
            })
    }
}