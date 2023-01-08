package jt.projects.gbpopularlibs

import android.app.Application
import jt.projects.gbpopularlibs.di.*

class App : Application(), IUserScopeContainer, IUserProfileScopeContainer {
    var userListSubcomponent: UserListSubcomponent? = null
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

    fun initUserListSubcomponent() = appComponent.userListSubcomponent().also {
        userListSubcomponent = it
    }

    fun initUserProfileSubcomponent() =
        userListSubcomponent?.userProfileSubcomponent().also {
            userProfileSubcomponent = it
        }


    override fun userScopeContainerRelease() {
        userListSubcomponent = null
    }

    override fun userProfileScopeContainerRelease() {
        userProfileSubcomponent = null
    }
}