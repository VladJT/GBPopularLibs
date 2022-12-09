package jt.projects.gbpopularlibs.utils

import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkRequest
import android.widget.Toast
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.subjects.BehaviorSubject

class NetworkStatus(context: Context) : INetworkStatus {
    //    BehaviorSubject хранит только последнее значение.
    //    Это то же самое, что и ReplaySubject, но с буфером размером 1.
    //    Во время создания ему может быть присвоено начальное значение, таким образом гарантируя,
    //    что данные всегда будут доступны новым подписчикам
    private val statusSubject: BehaviorSubject<Boolean> = BehaviorSubject.create()

    init {
        statusSubject.onNext(false)
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val request = NetworkRequest.Builder().build()
        connectivityManager.registerNetworkCallback(
            request,
            object : ConnectivityManager.NetworkCallback() {
                override fun onAvailable(network: Network) {
                    statusSubject.onNext(true)
                    Toast.makeText(context, "onAvailable", Toast.LENGTH_SHORT).show()
                }

                override fun onUnavailable() {
                    statusSubject.onNext(false)
                    Toast.makeText(context, "onUnavailable", Toast.LENGTH_SHORT).show()
                }

                override fun onLost(network: Network) {
                    statusSubject.onNext(false)
                    Toast.makeText(context, "onLost", Toast.LENGTH_SHORT).show()
                }

            })

    }

    override fun isOnline(): Observable<Boolean> = statusSubject
    override fun isOnlineSingle(): Single<Boolean> = statusSubject.first(false)
}