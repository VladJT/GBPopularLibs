package jt.projects.gbpopularlibs.data.users

import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import jt.projects.gbpopularlibs.data.retrofit.RetrofitDataSourceImpl
import jt.projects.gbpopularlibs.data.room.IUsersCache
import jt.projects.gbpopularlibs.domain.entities.UserEntity
import jt.projects.gbpopularlibs.utils.INetworkStatus

class UsersRepoRetrofitImpl(
    private val networkStatus: INetworkStatus,
    private val cacheImpl: IUsersCache
) : IUsersRepository {

    private val api = RetrofitDataSourceImpl().getApi()

    // мы позволяем репозиторию самостоятельно следить за тем, чтобы сетевые вызовы
    //выполнялись именно в io-потоке. Лучше поступать именно таким образом, даже когда речь не идёт о
    //сети, чтобы избежать выполнения операций в неверном потоке в вызывающем коде
    override fun getUsers(): Single<List<UserEntity>> =
        networkStatus.isOnlineSingle().flatMap { isOnline ->
            if (isOnline) {
                api.getUsers().map {
                    it.map { user -> user.toUserEntity() }
                }
                    .flatMap { users ->
                        Single.fromCallable {
                            cacheImpl.saveUsers(users)
                            users
                        }
                    }
            } else {
                Single.fromCallable {
                    cacheImpl.getUsers()
                }
            }
        }
}