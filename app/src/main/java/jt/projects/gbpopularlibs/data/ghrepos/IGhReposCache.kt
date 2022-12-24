package jt.projects.gbpopularlibs.data.ghrepos

import jt.projects.gbpopularlibs.domain.entities.GhRepoEntity


interface IGhReposCache {
    fun getReposByUserId(id: Int): List<GhRepoEntity>
    fun saveRepos(repos: List<GhRepoEntity>)
}