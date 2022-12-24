package jt.projects.gbpopularlibs.di

import android.content.Context
import android.net.ConnectivityManager
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import jt.projects.gbpopularlibs.App
import jt.projects.gbpopularlibs.core.utils.INetworkStatus
import jt.projects.gbpopularlibs.core.utils.NetworkStatus
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    @Singleton
    @Provides
    fun networkStatus(): INetworkStatus = NetworkStatus()

    @Singleton
    @Provides
    fun connectivityManager(app: App): ConnectivityManager =
        app.applicationContext
            .getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
}