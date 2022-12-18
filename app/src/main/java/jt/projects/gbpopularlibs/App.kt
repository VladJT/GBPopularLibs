package jt.projects.gbpopularlibs

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import androidx.room.Room
import com.github.terrakok.cicerone.Cicerone
import com.github.terrakok.cicerone.Router
import jt.projects.gbpopularlibs.core.utils.DB_NAME
import jt.projects.gbpopularlibs.core.utils.INetworkStatus
import jt.projects.gbpopularlibs.core.utils.NetworkStatus
import jt.projects.gbpopularlibs.data.room.AppDatabase

class App : Application() {
    companion object {
        lateinit var instance: App
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
    }


    /**
     * CICERONE
     */
    private val cicerone: Cicerone<Router> by lazy {
        Cicerone.create()
    }
    val navigatorHolder get() = cicerone.getNavigatorHolder()
    val router get() = cicerone.router


    /**
     * ROOM DATABASE
     */
    private var db: AppDatabase? = null
    fun getDatabase(): AppDatabase {
        if (db == null) {
            db = Room.databaseBuilder(instance.applicationContext, AppDatabase::class.java, DB_NAME)
        //        .fallbackToDestructiveMigration()
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