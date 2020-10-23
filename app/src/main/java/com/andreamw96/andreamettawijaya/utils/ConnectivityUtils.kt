package com.andreamw96.andreamettawijaya.utils

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo

fun isConnectInternet(context: Context): Boolean {
    val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    val activeNetwork: NetworkInfo? = cm.activeNetworkInfo

    return activeNetwork?.isConnectedOrConnecting == true
}