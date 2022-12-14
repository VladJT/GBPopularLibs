package jt.projects.gbpopularlibs.data.users

import jt.projects.gbpopularlibs.domain.entities.UserEntity


interface IUsersCache {
    fun getUsers(): List<UserEntity>
    fun saveUsers(users: List<UserEntity>)
}