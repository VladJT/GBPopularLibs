package jt.projects.gbnasaapp.model.mars

import jt.projects.gbpopularlibs.data.users.GithubUsersAPI
import jt.projects.gbpopularlibs.domain.entities.UserDTO
import jt.projects.gbpopularlibs.domain.entities.UserEntity
import jt.projects.gbpopularlibs.domain.interfaces.CommonCallback
import jt.projects.gbpopularlibs.domain.interfaces.UsersRepository
import jt.projects.gbpopularlibs.domain.retrofit.RetrofitImpl
import jt.projects.gbpopularlibs.utils.BASE_URL

class UsersRepoRetrofitImpl : RetrofitImpl(baseUrl = BASE_URL), UsersRepository {
    private val retrofitImpl = getRetrofitImpl<GithubUsersAPI>()

    override fun getUsers(callback: CommonCallback<List<UserEntity>>) {
        retrofitImpl.getUsers()
            .enqueue(getCallbackFromRetrofit(object : CommonCallback<List<UserDTO>> {
                override fun onSuccess(data: List<UserDTO>) {
                    val users = convertUsersDTOtoEntities(data)
                    callback.onSuccess(users)
                }

                override fun onFailure(e: Throwable) {
                    callback.onFailure(e)
                }
            }))
    }

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