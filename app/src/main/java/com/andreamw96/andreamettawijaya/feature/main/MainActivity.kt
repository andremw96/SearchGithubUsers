package com.andreamw96.andreamettawijaya.feature.main

import android.app.SearchManager
import android.content.Context
import android.os.Bundle
import android.view.Menu
import android.view.View
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.andreamw96.andreamettawijaya.R
import com.andreamw96.andreamettawijaya.di.viewmodel.ViewModelProvidersFactory
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : DaggerAppCompatActivity() {

    @Inject
    lateinit var viewModelProviderFactory: ViewModelProvidersFactory

    private lateinit var mainViewModel: MainViewModel
    private lateinit var searchView: SearchView
    private lateinit var adapter: MainAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        adapter = MainAdapter(this)
        rv_list.adapter = adapter

        observeMainViewModel()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.search, menu)

        val searchManager = getSystemService(Context.SEARCH_SERVICE) as SearchManager
        searchView = (menu?.findItem(R.id.action_search)?.actionView as SearchView).apply {
            setSearchableInfo(searchManager.getSearchableInfo(componentName))
        }

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                mainViewModel.getUsersByName(query, 1)
                searchView.clearFocus()

                return true
            }

            override fun onQueryTextChange(newText: String): Boolean {

                return true
            }
        })


        return true
    }

    private fun observeMainViewModel() {
        mainViewModel = ViewModelProvider(this, viewModelProviderFactory).get(MainViewModel::class.java)
        mainViewModel.userData?.observe(this, Observer {
            if (it.isNullOrEmpty()) {
                data_no_found.visibility = View.VISIBLE
            } else {
                data_no_found.visibility = View.GONE
            }
            adapter.bindData(it)
        })

        mainViewModel.isLoading.observe(this, Observer { isLoading ->
            if (isLoading) {
                progress_bar.show()
            } else {
                progress_bar.hide()
            }
        })

        mainViewModel.errorData.observe(this, Observer { errorMessage ->
            if (errorMessage != null) {
                data_no_found.visibility = View.VISIBLE
            } else {
                data_no_found.visibility = View.GONE
            }
            adapter.bindData(emptyList())
        })
    }
}