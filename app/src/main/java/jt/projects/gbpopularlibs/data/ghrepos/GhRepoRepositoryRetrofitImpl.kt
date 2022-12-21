package jt.projects.gbpopularlibs.data.ghrepos

import io.reactivex.rxjava3.core.Single
import jt.projects.gbpopularlibs.core.utils.INetworkStatus
import jt.projects.gbpopularlibs.data.retrofit.GithubAPI
import jt.projects.gbpopularlibs.domain.entities.GhRepoEntity
import jt.projects.gbpopularlibs.domain.entities.UserEntity

class GhRepoRepositoryRetrofitImpl(
    private val networkStatus: INetworkStatus,
    private val cacheImpl: IGhReposCache,
    private val api: GithubAPI
) : IGhReposRepository {

    override fun getUserGHRepos(user: UserEntity): Single<List<GhRepoEntity>> =
        networkStatus.isOnlineSingle().flatMap { isOnline ->
            if (isOnline) {
                fetchReposFromApiByUser(user)
            } else {
                getFromDbByUser(user)
            }
        }


    private fun fetchReposFromApiByUser(user: UserEntity) =
        api.getRepos(user.login)
            .map { repos ->
                repos.map { it.userId = user.id }
                repos
            }
            .flatMap { repos ->
                Single.fromCallable {
                    cacheImpl.saveRepos(repos)
                    repos
                }
            }


    private fun getFromDbByUser(user: UserEntity): Single<List<GhRepoEntity>> =
        Single.fromCallable {
            val repos = cacheImpl.getReposByUserId(user.id)
            if (repos.isEmpty()) {
                throw RuntimeException("Данных в локальном хранилище не найдено")
                //return@fromCallable listOf<UserGHRepo>(UserGHRepo("*", "no_data_from_cache", 0, -1))
            } else return@fromCallable repos
        }
}