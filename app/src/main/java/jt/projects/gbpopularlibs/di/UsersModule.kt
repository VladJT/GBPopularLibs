package jt.projects.gbpopularlibs.di

import dagger.Component
import dagger.Module
import dagger.Provides
import dagger.Subcomponent
import jt.projects.gbpopularlibs.App
import jt.projects.gbpopularlibs.core.utils.INetworkStatus
import jt.projects.gbpopularlibs.data.retrofit.GithubAPI
import jt.projects.gbpopularlibs.data.room.AppDatabase
import jt.projects.gbpopularlibs.data.users.IUsersCache
import jt.projects.gbpopularlibs.data.users.IUsersRepository
import jt.projects.gbpopularlibs.data.users.UsersCacheRoomImpl
import jt.projects.gbpopularlibs.data.users.UsersRepositoryRetrofitImpl
import jt.projects.gbpopularlibs.presenter.users.UsersPresenter
import javax.inject.Scope


@Scope
@Retention(AnnotationRetention.RUNTIME)
annotation class UsersScope

interface IUsersScopeContainer {
    fun usersSCRelease()
}


@Module
class UsersModule {
    @UsersScope
    @Provides
    fun usersCache(database: AppDatabase): IUsersCache = UsersCacheRoomImpl(database)

    @UsersScope
    @Provides
    fun usersRepo(
        networkStatus: INetworkStatus,
        cacheSource: IUsersCache,
        api: GithubAPI
    ): IUsersRepository =
        UsersRepositoryRetrofitImpl(networkStatus, cacheSource, api)

    @UsersScope
    @Provides
    fun scopeContainer(app: App): IUsersScopeContainer = app
}


@UsersScope
@Subcomponent(modules = [UsersModule::class])
interface UsersSubcomponent {
    fun userProfileSubcomponent(): UserProfileSubcomponent
    fun inject(usersPresenter: UsersPresenter)
}