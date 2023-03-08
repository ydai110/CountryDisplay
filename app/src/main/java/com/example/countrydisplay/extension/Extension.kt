package com.example.countrydisplay.extension

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities

/**
 * Check internet extension
 */
fun Context.checkInternet(): Boolean {
    val connectivityManager =
        this.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

    val network = connectivityManager.activeNetwork ?: return false

    val activeNetwork = connectivityManager.getNetworkCapabilities(network) ?: return false

    return when {
        activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true

        activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true

        else -> false
    }
}
