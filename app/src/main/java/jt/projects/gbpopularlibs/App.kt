package jt.projects.gbpopularlibs

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import jt.projects.gbpopularlibs.di.AppComponent
import jt.projects.gbpopularlibs.di.AppModule


@HiltAndroidApp
class App : Application() {
    companion object {
        lateinit var instance: App
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
    }
}