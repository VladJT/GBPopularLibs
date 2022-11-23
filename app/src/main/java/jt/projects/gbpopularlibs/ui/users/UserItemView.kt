package jt.projects.gbpopularlibs.ui.users

import jt.projects.gbpopularlibs.domain.entities.UserEntity

interface UserItemView : IItemView {
    fun bind(userEntity: UserEntity)
}