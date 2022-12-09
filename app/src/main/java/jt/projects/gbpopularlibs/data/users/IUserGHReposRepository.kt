package jt.projects.gbpopularlibs.data.users

import io.reactivex.rxjava3.core.Single
import jt.projects.gbpopularlibs.domain.entities.UserEntity
import jt.projects.gbpopularlibs.domain.entities.UserGHRepo

// Repo должен реализовывать команды CRUD
interface IUserGHReposRepository {
    fun getUserGHRepos(user: UserEntity): Single<List<UserGHRepo>>
}