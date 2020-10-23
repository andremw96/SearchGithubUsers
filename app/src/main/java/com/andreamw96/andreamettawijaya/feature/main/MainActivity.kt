package com.andreamw96.andreamettawijaya.feature.main

import android.app.SearchManager
import android.content.Context
import android.os.Bundle
import android.view.Menu
import android.view.View
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.andreamw96.andreamettawijaya.R
import com.andreamw96.andreamettawijaya.di.viewmodel.ViewModelProvidersFactory
import com.andreamw96.andreamettawijaya.utils.EndlessRecyclerViewScrollListener
import com.andreamw96.andreamettawijaya.utils.isConnectInternet
import com.andreamw96.andreamettawijaya.utils.toPresentationModel
import com.andreamw96.usecases.base.Resource
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : DaggerAppCompatActivity() {

    @Inject
    lateinit var viewModelProviderFactory: ViewModelProvidersFactory

    private lateinit var mainViewModel: MainViewModel
    private lateinit var searchView: SearchView
    private lateinit var adapter: MainAdapter

    var initialPage = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        adapter = MainAdapter(this)
        val layoutMnager = LinearLayoutManager(this)
        rv_list.adapter = adapter
        rv_list.layoutManager = layoutMnager
        rv_list.addOnScrollListener(object : EndlessRecyclerViewScrollListener(layoutMnager) {
            override fun onLoadMore(page: Int, totalItemsCount: Int) {
                if (isConnectInternet(this@MainActivity)) {
                    //mainViewModel.getSameUserNextPage(page+1)
                } else {
                    Toast.makeText(this@MainActivity, "No Internet Connection", Toast.LENGTH_LONG).show()
                }
            }
        })

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
               // mainViewModel.getUsersByName(query, initialPage)
                mainViewModel.setQuery(query)
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
        mainViewModel.getData.observe(this, Observer { resource ->
            if (resource != null) {
                when (resource.status) {
                    Resource.Status.LOADING -> {
                        progress_bar.show()
                    }
                    Resource.Status.SUCCESS -> {
                        progress_bar.hide()
                        resource.data?.map {
                            it.toPresentationModel()
                        }?.let { adapter.bindData(it) }
                    }
                    Resource.Status.ERROR -> {
                        progress_bar.hide()
                        data_no_found.visibility = View.VISIBLE
                    }
                }
            }
        })
       /* mainViewModel.userData.observe(this, Observer {
            if (it.isNullOrEmpty()) {
                data_no_found.visibility = View.VISIBLE
                adapter.clearData()
            } else {
                data_no_found.visibility = View.GONE
                adapter.bindData(it)
            }
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
            adapter.clearData()
        })

        mainViewModel.queryChanged.observe(this, Observer { queryChanged ->
            if (queryChanged) {
                adapter.clearData()
            }
        })*/
    }
}