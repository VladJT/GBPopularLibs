package jt.projects.gbpopularlibs.di

import android.content.Context
import dagger.Module
import dagger.Provides
import jt.projects.gbpopularlibs.App
import javax.inject.Singleton


@Module
class AppModule(val app: App) {

    @Singleton
    @Provides
    fun app(): App {
        return app
    }

//    @Singleton
//    @Provides
//    fun appContext(app: App): Context = app.applicationContext
}