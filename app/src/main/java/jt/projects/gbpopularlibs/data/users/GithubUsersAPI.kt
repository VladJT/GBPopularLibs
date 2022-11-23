package jt.projects.gbpopularlibs.data.users

import jt.projects.gbpopularlibs.domain.entities.UserDTO
import retrofit2.Call
import retrofit2.http.GET

interface GithubUsersAPI {
    @GET("users")
    fun getUsers(): Call<List<UserDTO>>
}