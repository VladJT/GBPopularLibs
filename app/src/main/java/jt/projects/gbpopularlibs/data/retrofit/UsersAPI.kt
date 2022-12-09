package jt.projects.gbpopularlibs.data.retrofit

import io.reactivex.rxjava3.core.Single
import jt.projects.gbpopularlibs.domain.entities.UserDTO
import jt.projects.gbpopularlibs.domain.entities.UserGHRepo
import retrofit2.http.GET
import retrofit2.http.Path

interface UsersAPI {
    @GET("users")
    fun getUsers(): Single<List<UserDTO>>

    @GET("users/{login}")
    fun loadUser(@Path("login") login: String): Single<UserDTO>

    @GET("users/{login}/repos")
    fun getRepos(@Path("login") login: String): Single<UserGHRepo>
}