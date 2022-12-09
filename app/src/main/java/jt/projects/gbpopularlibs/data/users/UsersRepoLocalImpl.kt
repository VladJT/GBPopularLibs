package jt.projects.gbpopularlibs.data.users

import android.os.Looper
import jt.projects.gbpopularlibs.domain.entities.UserEntity
import jt.projects.gbpopularlibs.interfaces.CommonCallback


private const val DATA_LOADING_DELAY = 1_000L

class UsersRepoLocalImpl {

    private val users = listOf(
        UserEntity("Vlad", 1, avatar_url = "https://avatars.githubusercontent.com/u/1?v=4"),
        UserEntity("Stas", 2, avatar_url = "https://avatars.githubusercontent.com/u/2?v=4"),
        UserEntity("Piter", 3, avatar_url = "https://avatars.githubusercontent.com/u/3?v=4"),
        UserEntity("Mike", 4, avatar_url = "https://avatars.githubusercontent.com/u/4?v=4")
    )


    fun getUsers(callback: CommonCallback<List<UserEntity>>) {
        android.os.Handler(Looper.getMainLooper()).postDelayed({
            callback.onSuccess(users)
        }, DATA_LOADING_DELAY)

    }
}