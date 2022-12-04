package jt.projects.gbnasaapp.model.mars

import io.reactivex.rxjava3.core.Single
import jt.projects.gbpopularlibs.data.users.GithubUsersAPIRetrofit
import jt.projects.gbpopularlibs.data.users.GithubUsersAPIrx
import jt.projects.gbpopularlibs.domain.entities.UserDTO
import jt.projects.gbpopularlibs.domain.entities.UserEntity
import jt.projects.gbpopularlibs.domain.interfaces.CommonCallback
import jt.projects.gbpopularlibs.domain.interfaces.UsersRepository
import jt.projects.gbpopularlibs.domain.retrofit.RetrofitImpl
import jt.projects.gbpopularlibs.utils.BASE_URL
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class UsersRepoRetrofitImpl : RetrofitImpl(baseUrl = BASE_URL), UsersRepository {
    private val retrofitImplStd = getRetrofitImpl<GithubUsersAPIRetrofit>()

    private val retrofitRxImpl =
        Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create()).build()
            .create(GithubUsersAPIrx::class.java)

    override fun getUsers(callback: CommonCallback<List<UserEntity>>) {
        retrofitImplStd.getUsers()
            .enqueue(getCallbackFromRetrofit(object : CommonCallback<List<UserDTO>> {
                override fun onSuccess(data: List<UserDTO>) {
                    val users = convertUsersDTOtoEntities(data)
                    callback.onSuccess(users)
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

    override fun getUsers(): Single<List<UserEntity>> =
        retrofitRxImpl.getUsers().map { x -> convertUsersDTOtoEntities(x) }


    private fun convertUsersDTOtoEntities(data: List<UserDTO>): List<UserEntity> {
        val result = mutableListOf<UserEntity>()
        for (userDto in data) {
            result.add(
                UserEntity(
                    id = userDto.id,
                    login = userDto.login,
                    avatar_url = userDto.avatar_url
                )
            )
        }
        return result
    }
}