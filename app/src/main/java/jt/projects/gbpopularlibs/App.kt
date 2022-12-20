package jt.projects.gbpopularlibs

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import androidx.room.Room
import jt.projects.gbpopularlibs.core.utils.DB_NAME
import jt.projects.gbpopularlibs.core.utils.INetworkStatus
import jt.projects.gbpopularlibs.core.utils.NetworkStatus
import jt.projects.gbpopularlibs.data.room.AppDatabase
import jt.projects.gbpopularlibs.di.AppComponent
import jt.projects.gbpopularlibs.di.AppModule
import jt.projects.gbpopularlibs.di.DaggerAppComponent

class App : Application() {
    companion object {
        lateinit var instance: App
    }

    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        instance = this
        appComponent = DaggerAppComponent.builder().appModule(AppModule(this)).build()
    }



//    /**
//     * NETWORK STATUS
//     */
//    private var networkStatus: INetworkStatus? = null
//    fun getNetworkStatus(): INetworkStatus {
//        if (networkStatus == null) {
//            val connectivityManager =
//                applicationContext.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
//            networkStatus = NetworkStatus(connectivityManager)
//        }
//        return networkStatus as INetworkStatus
//    }
}