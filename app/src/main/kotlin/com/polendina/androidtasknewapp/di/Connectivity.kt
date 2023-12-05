package com.polendina.androidtasknewapp.di

import android.content.Context
import android.net.ConnectivityManager

class NetworkConnectivity(
    context: Context
) {
    val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    fun isNetworkAvailable(): Boolean {
        return connectivityManager.activeNetworkInfo?.isConnected ?: false
    }
}