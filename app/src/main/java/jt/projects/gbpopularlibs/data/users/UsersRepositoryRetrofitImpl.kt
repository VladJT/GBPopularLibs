package jt.projects.gbpopularlibs.data.users

import io.reactivex.rxjava3.core.Single
import jt.projects.gbpopularlibs.data.retrofit.RetrofitDataSourceImpl
import jt.projects.gbpopularlibs.data.room.IUsersCache
import jt.projects.gbpopularlibs.domain.entities.UserEntity
import jt.projects.gbpopularlibs.utils.INetworkStatus

class UsersRepositoryRetrofitImpl(
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
                    }.onErrorReturn { throw RuntimeException("Ошибка получения данных по http") }
            } else {
                Single.fromCallable {
                    val users = cacheImpl.getUsers()
                    if (users.isEmpty()) {
                        throw RuntimeException("Данных в локальном хранилище не найдено")
                    } else return@fromCallable users
                }
                    .onErrorReturn { throw RuntimeException("Ошибка получения данных из локального хранилища") }
            }
        }
}