package jt.projects.gbpopularlibs.model.interfaces

import jt.projects.gbpopularlibs.model.UserEntity

interface GithubUsersRepository {
    fun getUsers(): List<UserEntity>
}