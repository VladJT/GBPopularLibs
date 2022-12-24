package jt.projects.gbpopularlibs.di

import com.github.terrakok.cicerone.Cicerone
import com.github.terrakok.cicerone.NavigatorHolder
import com.github.terrakok.cicerone.Router
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import jt.projects.gbpopularlibs.core.nav.AndroidScreens
import jt.projects.gbpopularlibs.core.nav.IScreens
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class CiceroneModule {
    private val cicerone: Cicerone<Router> = Cicerone.create()

    @Singleton
    @Provides
    fun cicerone(): Cicerone<Router> = cicerone

    @Singleton
    @Provides
    fun navigatorHolder(): NavigatorHolder = cicerone.getNavigatorHolder()

    @Singleton
    @Provides
    fun router(): Router = cicerone.router

    @Singleton
    @Provides
    fun screens(): IScreens = AndroidScreens()
}