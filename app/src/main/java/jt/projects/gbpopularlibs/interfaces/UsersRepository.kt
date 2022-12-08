package jt.projects.gbpopularlibs.interfaces

import io.reactivex.rxjava3.core.Single
import jt.projects.gbpopularlibs.domain.entities.UserEntity

// Repo должен реализовывать команды CRUD
interface UsersRepository {
    fun getUsers(): Single<List<UserEntity>>
}