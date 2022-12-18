package jt.projects.gbpopularlibs.core.utils

import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkRequest
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.subjects.BehaviorSubject

class NetworkStatus(connectivityManager: ConnectivityManager) : INetworkStatus {
    private val statusSubject: BehaviorSubject<Boolean> = BehaviorSubject.create()

    init {
        statusSubject.onNext(false)
        val request = NetworkRequest.Builder().build()
        connectivityManager.registerNetworkCallback(
            request,
            object : ConnectivityManager.NetworkCallback() {
                override fun onAvailable(network: Network) = statusSubject.onNext(true)
                override fun onUnavailable() = statusSubject.onNext(false)
                override fun onLost(network: Network) = statusSubject.onNext(false)
            })
    }

    override fun isOnline(): Observable<Boolean> = statusSubject
    override fun isOnlineSingle(): Single<Boolean> = statusSubject.first(false)
}