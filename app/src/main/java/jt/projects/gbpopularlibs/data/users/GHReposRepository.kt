package jt.projects.gbpopularlibs.data.users

import io.reactivex.rxjava3.core.Single
import jt.projects.gbpopularlibs.data.retrofit.RetrofitDataSourceImpl
import jt.projects.gbpopularlibs.data.room.IUsersCache
import jt.projects.gbpopularlibs.domain.entities.UserEntity
import jt.projects.gbpopularlibs.domain.entities.UserGHRepo
import jt.projects.gbpopularlibs.utils.INetworkStatus

class GHReposRepository(
    private val networkStatus: INetworkStatus,
    private val cacheImpl: IUsersCache
) : IUserGHReposRepository {

    private val api = RetrofitDataSourceImpl().getApi()

    override fun getUserGHRepos(user: UserEntity): Single<List<UserGHRepo>> =
        networkStatus.isOnlineSingle().flatMap { isOnline ->
            if (isOnline) {
                api.getRepos(user.login).map { repos->
                    repos.map { it.userId = user.id }
                    repos
                }
                    .flatMap { repos ->
                        Single.fromCallable {
                            cacheImpl.saveRepos(repos)
                            repos
                        }
                    }
            } else {
                Single.fromCallable {
                    val repos = cacheImpl.getReposByUserId(user.id)
                 //   val repos = cacheImpl.getAllRepos()
                    if (repos.isEmpty()) {
                        return@fromCallable listOf<UserGHRepo>(UserGHRepo("*", "no_data_from_cache", 0, -1))
                    }else  return@fromCallable repos
                }
            }
        }
}