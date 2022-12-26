package jt.projects.gbpopularlibs.di

import dagger.Module
import dagger.Provides
import jt.projects.gbpopularlibs.core.utils.INetworkStatus
import jt.projects.gbpopularlibs.data.ghrepos.GhRepoRepositoryRetrofitImpl
import jt.projects.gbpopularlibs.data.ghrepos.IGhReposCache
import jt.projects.gbpopularlibs.data.ghrepos.IGhReposRepository
import jt.projects.gbpopularlibs.data.retrofit.GithubAPI
import jt.projects.gbpopularlibs.data.users.IUsersCache
import jt.projects.gbpopularlibs.data.users.IUsersRepository
import jt.projects.gbpopularlibs.data.users.UsersRepositoryRetrofitImpl
import javax.inject.Singleton



@Module
class RepoModule {

    @Singleton
    @Provides
    fun usersRepo(
        networkStatus: INetworkStatus,
        cacheSource: IUsersCache,
        api: GithubAPI
    ): IUsersRepository =
        UsersRepositoryRetrofitImpl(networkStatus, cacheSource, api)

    @Singleton
    @Provides
    fun usersGHReposRepo(
        networkStatus: INetworkStatus,
        cacheSource: IGhReposCache,
        api: GithubAPI
    ): IGhReposRepository =
        GhRepoRepositoryRetrofitImpl(networkStatus, cacheSource, api)
}