package jt.projects.gbpopularlibs.data.room

import jt.projects.gbpopularlibs.domain.entities.GhRepoEntity

class GhReposCacheRoomImpl(private val db: AppDatabase) : IGhReposCache {
    override fun getReposByUserId(id: Int): List<GhRepoEntity> =
        db.ghReposDao().findReposByUserId(id)

    override fun saveRepos(repos: List<GhRepoEntity>) = db.ghReposDao().insert(repos)
}