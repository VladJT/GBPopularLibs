package jt.projects.gbpopularlibs.domain

import io.reactivex.rxjava3.core.Single
import jt.projects.gbpopularlibs.domain.entities.UserEntity
import jt.projects.gbpopularlibs.interfaces.CommonCallback

// Repo должен реализовывать команды CRUD
interface UsersRepository {
    fun getUsers(callback: CommonCallback<List<UserEntity>>)
    fun getUsers(): Single<List<UserEntity>>
}