package jt.projects.gbpopularlibs.model

import android.os.Looper
import jt.projects.gbpopularlibs.model.interfaces.CommonCallback
import jt.projects.gbpopularlibs.model.interfaces.UsersRepository


private const val DATA_LOADING_DELAY = 1_000L

class UsersRepositoryLocalImpl : UsersRepository {

    private val users = listOf(
        UserEntity("Vlad", 1, avatar_url = "https://avatars.githubusercontent.com/u/1?v=4"),
        UserEntity("Stas", 2, avatar_url = "https://avatars.githubusercontent.com/u/2?v=4"),
        UserEntity("Piter", 3, avatar_url = "https://avatars.githubusercontent.com/u/3?v=4"),
        UserEntity("Mike", 4, avatar_url = "https://avatars.githubusercontent.com/u/4?v=4")
    )


    override fun getUsers(callback: CommonCallback<List<UserEntity>>) {
        android.os.Handler(Looper.getMainLooper()).postDelayed({
            callback.onSuccess(users)
        }, DATA_LOADING_DELAY)

    }


}