package jt.projects.gbpopularlibs

import android.app.Application
import android.util.Log
import jt.projects.gbpopularlibs.core.utils.DEBUG_TAG
import jt.projects.gbpopularlibs.di.*

class App : Application(), IUsersScopeContainer, IUserProfileScopeContainer {
    var usersSubcomponent: UsersSubcomponent? = null
        private set

    var userProfileSubcomponent: UserProfileSubcomponent? = null
        private set


    companion object {
        lateinit var instance: App
    }

    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        instance = this
        appComponent = DaggerAppComponent.builder().appModule(AppModule(this)).build()
    }

    fun initUsersSubcomponent() = appComponent.userListSubcomponent().also {
        usersSubcomponent = it
        Log.d(DEBUG_TAG, "UsersSubcomponent INIT")
    }

    fun initUserProfileSubcomponent() =
        usersSubcomponent?.userProfileSubcomponent().also {
            userProfileSubcomponent = it
            Log.d(DEBUG_TAG, "userProfileSubcomponent INIT")
        }


    override fun usersSCRelease() {
        usersSubcomponent = null
        Log.d(DEBUG_TAG, "usersSubcomponent RELEASE")
    }

    override fun userProfileSCRelease() {
        userProfileSubcomponent = null
        Log.d(DEBUG_TAG, "userProfileSubcomponent RELEASE")
    }
}