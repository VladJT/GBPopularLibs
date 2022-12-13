package jt.projects.gbpopularlibs.data.room

import jt.projects.gbpopularlibs.domain.entities.UserEntity
import jt.projects.gbpopularlibs.domain.entities.UserGHRepo

class UsersCacheRoomImpl(private val db: AppDatabase) : IUsersCache {

    override fun getUsers() = db.userDao().getAll()
    override fun saveUsers(users: List<UserEntity>) = db.userDao().insert(users)

    override fun getReposByUserId(id: Int): List<UserGHRepo> = db.repositoryDao().findReposByUserId(id)
    override fun getAllRepos() = db.repositoryDao().getAll()
    override fun saveRepos(repos: List<UserGHRepo>)= db.repositoryDao().insert(repos)
}