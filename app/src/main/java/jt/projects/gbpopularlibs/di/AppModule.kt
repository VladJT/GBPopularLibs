package jt.projects.gbpopularlibs.di

import dagger.Module
import dagger.Provides
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import jt.projects.gbpopularlibs.App
import javax.inject.Singleton

@AssistedFactory
interface AppModuleFactory {
    fun createApp(app: App): AppModule
}

@Module
@InstallIn(SingletonComponent::class)
class AppModule @AssistedInject constructor(@Assisted val app: App) {
    @Provides
    fun provideApp(): App = app

    @Provides
    fun app(): App {
        return app
    }
}