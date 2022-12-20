package jt.projects.gbpopularlibs.di

import android.content.Context
import android.net.ConnectivityManager
import dagger.Module
import dagger.Provides
import jt.projects.gbpopularlibs.App

@Module
class AppModule(val app: App) {
    @Provides
    fun app(): App {
        return app
    }

    @Provides
    fun connectivityManager(app: App): ConnectivityManager =
        app.applicationContext
            .getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

}