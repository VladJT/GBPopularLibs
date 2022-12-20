package jt.projects.gbpopularlibs.di

import dagger.Module
import dagger.Provides
import jt.projects.gbpopularlibs.core.utils.INetworkStatus
import jt.projects.gbpopularlibs.core.utils.NetworkStatus
import javax.inject.Singleton

@Module
class NetworkModule {

    @Singleton
    @Provides
    fun networkStatus(): INetworkStatus = NetworkStatus()
}