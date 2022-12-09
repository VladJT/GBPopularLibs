package jt.projects.gbpopularlibs.data.room

import jt.projects.gbpopularlibs.domain.entities.UserEntity

class UsersCacheRoomImpl(private val db: AppDatabase) : IUsersCache {
    override fun getUsers() = db.userDao().getAll()

    override fun saveUsers(users: List<UserEntity>) = db.userDao().insert(users)
}