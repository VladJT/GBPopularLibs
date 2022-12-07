package jt.projects.gbnasaapp.model.mars

import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import jt.projects.gbpopularlibs.data.users.BaseRetrofit
import jt.projects.gbpopularlibs.data.users.GithubUsersAPIRetrofit
import jt.projects.gbpopularlibs.data.users.GithubUsersAPIrx
import jt.projects.gbpopularlibs.domain.entities.UserDTO
import jt.projects.gbpopularlibs.domain.entities.UserEntity
import jt.projects.gbpopularlibs.interfaces.CommonCallback
import jt.projects.gbpopularlibs.interfaces.UsersRepository
import jt.projects.gbpopularlibs.utils.BASE_URL
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class UsersRepoBaseRetrofit : BaseRetrofit(baseUrl = BASE_URL), UsersRepository {
    private val retrofitImplStd = getRetrofitImpl<GithubUsersAPIRetrofit>()

    private val retrofitRxImpl =
        Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create()).build()
            .create(GithubUsersAPIrx::class.java)

    override fun getUsers(callback: CommonCallback<List<UserEntity>>) {
        retrofitImplStd.getUsers()
            .enqueue(getCallbackFromRetrofit(object : CommonCallback<List<UserDTO>> {
                override fun onSuccess(data: List<UserDTO>) {
                    callback.onSuccess(data.map { it.toUserEntity() })
                }

                override fun onFailure(e: Throwable) {
                    callback.onFailure(e)
                }
            }))

//        retrofitImpl.getUsers().subscribe(
//            {
//                val users = convertUsersDTOtoEntities(it)
//                callback.onSuccess(users)
//            },
//            {
//                callback.onFailure(it)
//            })
    }

    //Таким образом, мы позволяем репозиторию самостоятельно следить за тем, чтобы сетевые вызовы
    //выполнялись именно в io-потоке. Лучше поступать именно таким образом, даже когда речь не идёт о
    //сети, чтобы избежать выполнения операций в неверном потоке в вызывающем коде
    override fun getUsers(): Single<List<UserEntity>> =
        retrofitRxImpl.getUsers().map { users ->
            users.map {
                it.toUserEntity()
            }
        }.subscribeOn(Schedulers.io())


}