package jt.projects.gbpopularlibs.di

import dagger.Module
import dagger.Provides
import jt.projects.gbpopularlibs.App

@Module
class AppModule(val app: App) {
    @Provides
    fun app(): App {
        return app
    }
}