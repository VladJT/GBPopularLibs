package jt.projects.gbpopularlibs.data.users

import io.reactivex.rxjava3.core.Single
import jt.projects.gbpopularlibs.domain.entities.UserDTO
import retrofit2.http.GET
import retrofit2.http.Path

interface UsersAPI {
    @GET("users")
    fun getUsers(): Single<List<UserDTO>>

    @GET("users/{login}")
    fun loadUser(@Path("login") login: String): Single<UserDTO>
}