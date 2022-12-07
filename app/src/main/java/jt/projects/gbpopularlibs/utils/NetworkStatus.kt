package jt.projects.gbpopularlibs.utils

import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkRequest
import android.widget.Toast
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.subjects.PublishSubject

class NetworkStatus(context: Context) {
    private val statusSubject: PublishSubject<Boolean> = PublishSubject.create()

    init {
        statusSubject.onNext(false)
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE)
                as ConnectivityManager
        val request = NetworkRequest.Builder().build()
        connectivityManager.registerNetworkCallback(request, object :
            ConnectivityManager.NetworkCallback() {
            override fun onAvailable(network: Network) {
                //Вызывается когда сеть есть сразу после запроса, либо когда появилась после отсутствия.
                Toast.makeText(context, "onAvailable", Toast.LENGTH_SHORT).show()
                statusSubject.onNext(true)
            }

            override fun onUnavailable() {
                //Вызывается, когда сеть не обнаружена после запроса
                Toast.makeText(context, "onUnavailable", Toast.LENGTH_SHORT).show()
                statusSubject.onNext(false)
            }

            override fun onLost(network: Network) {
                //Вызывается, когда сеть потеряна
                Toast.makeText(context, "onLost", Toast.LENGTH_SHORT).show()
                statusSubject.onNext(false)
            }
        })
    }

    fun status(): Observable<Boolean> = statusSubject

}