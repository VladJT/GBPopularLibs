package jt.projects.gbpopularlibs.di

import dagger.Module
import dagger.Provides
import dagger.Subcomponent
import jt.projects.gbpopularlibs.App
import jt.projects.gbpopularlibs.core.utils.INetworkStatus
import jt.projects.gbpopularlibs.data.ghrepos.GhRepoRepositoryRetrofitImpl
import jt.projects.gbpopularlibs.data.ghrepos.GhReposCacheRoomImpl
import jt.projects.gbpopularlibs.data.ghrepos.IGhReposCache
import jt.projects.gbpopularlibs.data.ghrepos.IGhReposRepository
import jt.projects.gbpopularlibs.data.retrofit.GithubAPI
import jt.projects.gbpopularlibs.data.room.AppDatabase
import jt.projects.gbpopularlibs.presenter.profile.UserProfilePresenter
import jt.projects.gbpopularlibs.presenter.users.UsersPresenter
import javax.inject.Scope

@Module
class UserProfileModule {
    @Provides
    fun cacheSource(database: AppDatabase): IGhReposCache = GhReposCacheRoomImpl(database)

    @UserProfileScope
    @Provides
    fun usersGHReposRepo(
        networkStatus: INetworkStatus,
        cacheSource: IGhReposCache,
        api: GithubAPI
    ): IGhReposRepository =
        GhRepoRepositoryRetrofitImpl(networkStatus, cacheSource, api)

    @UserProfileScope
    @Provides
    fun scopeContainer(app: App): IUserProfileScopeContainer = app
}