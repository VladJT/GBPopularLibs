package jt.projects.gbpopularlibs.data.users

import io.reactivex.rxjava3.core.Single
import jt.projects.gbpopularlibs.domain.entities.UserEntity

// Repo должен реализовывать команды CRUD
interface IUsersRepository {
    fun getUsers(): Single<List<UserEntity>>
}