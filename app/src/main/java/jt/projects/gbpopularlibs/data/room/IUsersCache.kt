package jt.projects.gbpopularlibs.data.room

import jt.projects.gbpopularlibs.domain.entities.UserEntity
import jt.projects.gbpopularlibs.domain.entities.UserGHRepo

interface IUsersCache {
    fun getUsers(): List<UserEntity>
    fun saveUsers(users: List<UserEntity>)
//    fun getsReposByUser(login: String): List<UserGHRepo>
//    fun saveRepos(repos: List<UserEntity>)
}