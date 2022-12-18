package jt.projects.gbpopularlibs.ui.profile

import jt.projects.gbpopularlibs.domain.entities.GhRepoEntity
import jt.projects.gbpopularlibs.ui.users.IItemView

interface RepoItemView : IItemView {
    fun bind(repoEntity: GhRepoEntity)
}