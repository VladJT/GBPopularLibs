package jt.projects.gbpopularlibs.data.users

import io.reactivex.rxjava3.core.Single
import jt.projects.gbpopularlibs.domain.entities.UserEntity
import java.util.concurrent.TimeUnit


private const val DATA_LOADING_DELAY = 2_000L

class UsersRepoLocalImpl : IUsersRepository {

    private val users = listOf(
        UserEntity("Vlad", 1, avatar_url = "https://avatars.githubusercontent.com/u/1?v=4"),
        UserEntity("Stas", 2, avatar_url = "https://avatars.githubusercontent.com/u/2?v=4"),
        UserEntity("Piter", 3, avatar_url = "https://avatars.githubusercontent.com/u/3?v=4"),
        UserEntity("Mike", 4, avatar_url = "https://avatars.githubusercontent.com/u/4?v=4")
    )


    override fun getUsers(): Single<List<UserEntity>> = Single.fromCallable { users }.delay(DATA_LOADING_DELAY,TimeUnit.MILLISECONDS)
}