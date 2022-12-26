package jt.projects.gbpopularlibs.di

import android.content.Context
import android.net.ConnectivityManager
import dagger.Module
import dagger.Provides
import jt.projects.gbpopularlibs.App
import jt.projects.gbpopularlibs.core.utils.INetworkStatus
import jt.projects.gbpopularlibs.core.utils.NetworkStatus
import javax.inject.Singleton


@Module
class NetworkModule {

    @Singleton
    @Provides
    fun networkStatus(appContext: Context): INetworkStatus = NetworkStatus(appContext)

    @Singleton
    @Provides
    fun connectivityManager(app: App): ConnectivityManager =
        app.applicationContext
            .getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
}