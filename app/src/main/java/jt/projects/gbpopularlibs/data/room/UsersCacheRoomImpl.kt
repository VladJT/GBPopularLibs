package jt.projects.gbpopularlibs.data.room

import jt.projects.gbpopularlibs.domain.entities.UserEntity

class UsersCacheRoomImpl(private val db: AppDatabase) : IUsersCache {
    override fun getUsers() = db.usersDao().getAll()
    override fun saveUsers(users: List<UserEntity>) = db.usersDao().insert(users)
}