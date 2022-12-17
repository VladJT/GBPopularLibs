package jt.projects.gbpopularlibs.data.retrofit

import io.reactivex.rxjava3.core.Single
import jt.projects.gbpopularlibs.domain.entities.GhRepoEntity
import jt.projects.gbpopularlibs.domain.entities.UserDTO
import retrofit2.http.GET
import retrofit2.http.Path

interface GithubAPI {
    @GET("users")
    fun getUsers(): Single<List<UserDTO>>

    @GET("users/{login}")
    fun getUser(@Path("login") login: String): Single<UserDTO>

    @GET("users/{login}/repos")
    fun getRepos(@Path("login") login: String): Single<List<GhRepoEntity>>
}