package jt.projects.gbpopularlibs.data.users

import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import jt.projects.gbpopularlibs.data.room.AppDatabase
import jt.projects.gbpopularlibs.domain.entities.UserEntity
import jt.projects.gbpopularlibs.utils.BASE_URL
import jt.projects.gbpopularlibs.utils.INetworkStatus
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class UsersRepositoryImpl(val networkStatus: INetworkStatus, val db: AppDatabase) : IUserRepository {
    private val retrofitImpl =
        Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create()).build()
            .create(UsersAPI::class.java)

    // мы позволяем репозиторию самостоятельно следить за тем, чтобы сетевые вызовы
    //выполнялись именно в io-потоке. Лучше поступать именно таким образом, даже когда речь не идёт о
    //сети, чтобы избежать выполнения операций в неверном потоке в вызывающем коде
    override fun getUsers(): Single<List<UserEntity>> =
        networkStatus.isOnlineSingle().flatMap { isOnline ->
            if (isOnline) {
                retrofitImpl.getUsers().map {
                    it.map { user -> user.toUserEntity() }
                }
                    .flatMap { users ->
                        Single.fromCallable {
                            db.userDao().insert(users)
                            users
                        }
                    }
            } else {
                Single.fromCallable {
                    db.userDao().getAll()
                }
            }
        }.subscribeOn(Schedulers.io())

}