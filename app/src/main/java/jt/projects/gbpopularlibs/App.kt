package jt.projects.gbpopularlibs

import android.app.Application
import dagger.hilt.EntryPoint
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.HiltAndroidApp

import javax.inject.Inject


@HiltAndroidApp
class App : Application() {

    companion object {
        lateinit var instance: App
    }

  //  lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        instance = this
     //   appComponent = DaggerAppComponent.builder().appModule(AppModule(this)).build()
    }
}