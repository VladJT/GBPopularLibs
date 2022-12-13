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
                api.getRepos(user.login)
                    .flatMap { repos ->
                        Single.fromCallable {
                        //    cacheImpl.saveUsers(users)
                            repos
                        }
                    }
            } else {
                Single.fromCallable {
                  //  cacheImpl.()
                    listOf<UserGHRepo>(UserGHRepo("id","no_name", 4, "uid"))
                }
            }
        }
}