package jt.projects.gbpopularlibs

import android.app.Application
import jt.projects.gbpopularlibs.koin.*
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin


class App : Application() {
    companion object {
        lateinit var instance: App
    }

    //   lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        instance = this
        startKoin {
            androidLogger()
            androidContext(this@App)
            modules(
                listOf(
                    appModule,
                    roomModule,
                    retrofitModule,
                    networkModule,
                    navModule,
                    vmModule
                )
            )
        }
        //     appComponent = DaggerAppComponent.builder().appModule(AppModule(this)).build()
    }
}