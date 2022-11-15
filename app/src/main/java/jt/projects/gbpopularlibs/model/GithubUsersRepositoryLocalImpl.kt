package jt.projects.gbpopularlibs.model

import jt.projects.gbpopularlibs.model.interfaces.GithubUsersRepository

class GithubUsersRepositoryLocalImpl : GithubUsersRepository {

    private val users = listOf(
        GithubUser("Vlad"), GithubUser("Stas"),
        GithubUser("Piter"), GithubUser("Mike")
    )

    override fun getUsers(): List<GithubUser> = users
}