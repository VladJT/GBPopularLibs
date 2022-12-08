package jt.projects.gbpopularlibs

import android.app.Application
import androidx.room.Room
import com.github.terrakok.cicerone.Cicerone
import com.github.terrakok.cicerone.Router
import jt.projects.gbpopularlibs.data.users.AppDatabase
import jt.projects.gbpopularlibs.utils.DB_NAME

class App : Application() {
    companion object {
        lateinit var instance: App
    }

    private val cicerone: Cicerone<Router> by lazy {
        Cicerone.create()
    }

    val navigatorHolder get() = cicerone.getNavigatorHolder()
    val router get() = cicerone.router

    override fun onCreate() {
        super.onCreate()
        instance = this
    }


    private var db: AppDatabase? = null

    fun getDatabase(): AppDatabase {
        if (db == null) {
            db = Room.databaseBuilder(instance.applicationContext, AppDatabase::class.java, DB_NAME)
                .fallbackToDestructiveMigration()
                .build()
        }
        return db!!
    }


}