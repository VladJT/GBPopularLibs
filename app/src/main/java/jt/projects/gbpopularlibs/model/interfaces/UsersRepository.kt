package jt.projects.gbpopularlibs.model.interfaces

import jt.projects.gbpopularlibs.model.UserEntity

// Repo должен реализовывать команды CRUD
interface UsersRepository {
    fun getUsers(callback: CommonCallback<List<UserEntity>>)
}