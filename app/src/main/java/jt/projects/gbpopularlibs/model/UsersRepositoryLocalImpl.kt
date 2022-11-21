package jt.projects.gbpopularlibs.model

import jt.projects.gbpopularlibs.model.interfaces.GithubUsersRepository

class UsersRepositoryLocalImpl : GithubUsersRepository {

    private val users = listOf(
        UserEntity("Vlad"), UserEntity("Stas", avatar_url = "https://avatars.githubusercontent.com/u/1?v=4"),
        UserEntity("Piter"), UserEntity("Mike")
    )

    override fun getUsers(): List<UserEntity> = users
}