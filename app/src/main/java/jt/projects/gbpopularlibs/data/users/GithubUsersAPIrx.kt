package jt.projects.gbpopularlibs.data.users

import io.reactivex.rxjava3.core.Single
import jt.projects.gbpopularlibs.domain.entities.UserDTO
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface GithubUsersAPIRetrofit {
    @GET("users")
    fun getUsers(): Call<List<UserDTO>>
}

interface GithubUsersAPIrx {
    @GET("users")
    fun getUsers(): Single<List<UserDTO>>

    @GET("users/{login}")
    fun loadUser(@Path("login") login: String): Single<UserDTO>

}