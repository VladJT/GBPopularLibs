package jt.projects.gbpopularlibs

import android.app.Application
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
}