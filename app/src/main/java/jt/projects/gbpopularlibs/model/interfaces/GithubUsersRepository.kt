package jt.projects.gbpopularlibs.model.interfaces

import jt.projects.gbpopularlibs.model.GithubUser

interface GithubUsersRepository {
    fun getUsers(): List<GithubUser>
}