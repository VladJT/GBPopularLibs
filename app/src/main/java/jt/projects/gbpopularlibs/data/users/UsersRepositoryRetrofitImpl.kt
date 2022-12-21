package jt.projects.gbpopularlibs.data.users

import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import jt.projects.gbpopularlibs.core.utils.INetworkStatus
import jt.projects.gbpopularlibs.data.retrofit.GithubAPI
import jt.projects.gbpopularlibs.domain.entities.UserEntity

class UsersRepositoryRetrofitImpl(
    private val networkStatus: INetworkStatus,
    private val cacheImpl: IUsersCache,
    private val api: GithubAPI
) : IUsersRepository {

    // мы позволяем репозиторию самостоятельно следить за тем, чтобы сетевые вызовы
    //выполнялись именно в io-потоке. Лучше поступать именно таким образом, даже когда речь не идёт о
    //сети, чтобы избежать выполнения операций в неверном потоке в вызывающем коде
    override fun getUsers(): Single<List<UserEntity>> =
        networkStatus.isOnlineSingle().flatMap { isOnline ->
            if (isOnline) {
                fetchFromApi()
            } else {
                getFromDb()
            }
        }.subscribeOn(Schedulers.io())


    private fun fetchFromApi() =
        api.getUsers()
            .map {
                it.map { user -> user.toUserEntity() }
            }
            .flatMap { users ->
                Single.fromCallable {
                    cacheImpl.saveUsers(users)
                    users
                }
            }.onErrorReturn { throw RuntimeException("Ошибка получения данных по http") }


    private fun getFromDb(): Single<List<UserEntity>> =
        Single.fromCallable {
            val users = cacheImpl.getUsers()
            if (users.isEmpty()) {
                throw RuntimeException("Данных в локальном хранилище не найдено")
            } else return@fromCallable users
        }
            .onErrorReturn { throw RuntimeException("Ошибка получения данных из локального хранилища") }

}