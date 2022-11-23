package jt.projects.gbpopularlibs.domain.interfaces

import jt.projects.gbpopularlibs.domain.entities.UserEntity

// Repo должен реализовывать команды CRUD
interface UsersRepository {
    fun getUsers(callback: CommonCallback<List<UserEntity>>)
}