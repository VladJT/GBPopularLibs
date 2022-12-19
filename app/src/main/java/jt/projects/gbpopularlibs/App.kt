package jt.projects.gbpopularlibs

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import androidx.room.Room
import dagger.Component
import dagger.Module
import dagger.Provides
import jt.projects.gbpopularlibs.core.nav.CiceroneModule
import jt.projects.gbpopularlibs.core.utils.DB_NAME
import jt.projects.gbpopularlibs.core.utils.INetworkStatus
import jt.projects.gbpopularlibs.core.utils.NetworkStatus
import jt.projects.gbpopularlibs.data.room.AppDatabase
import jt.projects.gbpopularlibs.presenter.counters.CounterPresenter
import jt.projects.gbpopularlibs.presenter.main.MainPresenter
import jt.projects.gbpopularlibs.presenter.profile.UserProfilePresenter
import jt.projects.gbpopularlibs.presenter.users.UsersPresenter
import jt.projects.gbpopularlibs.ui.main.MainActivity
import jt.projects.gbpopularlibs.viewmodel.CounterViewModel
import javax.inject.Singleton


@Module
class AppModule(val app: App) {
    @Provides
    fun app(): App {
        return app
    }
}

@Singleton
@Component( modules = [AppModule::class, CiceroneModule::class])
interface AppComponent {
    fun inject(mainActivity: MainActivity)
    fun inject(mainPresenter: MainPresenter)
    fun inject(usersPresenter: UsersPresenter)
    fun inject(userProfilePresenter: UserProfilePresenter)
    fun inject(counterPresenter: CounterPresenter)
    fun inject(counterViewModel: CounterViewModel)
}


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


    /**
     * ROOM DATABASE
     */
    private var db: AppDatabase? = null
    fun getDatabase(): AppDatabase {
        if (db == null) {
            db = Room.databaseBuilder(instance.applicationContext, AppDatabase::class.java, DB_NAME)
                .fallbackToDestructiveMigration()
                .build()
        }
        return db!!
    }

    /**
     * NETWORK STATUS
     */
    private var networkStatus: INetworkStatus? = null
    fun getNetworkStatus(): INetworkStatus {
        if (networkStatus == null) {
            val connectivityManager =
                applicationContext.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            networkStatus = NetworkStatus(connectivityManager)
        }
        return networkStatus as INetworkStatus
    }
}