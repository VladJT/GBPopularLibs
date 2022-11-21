package jt.projects.gbpopularlibs.ui.interfaces

import jt.projects.gbpopularlibs.model.UserEntity

interface UserItemView : IItemView {
    fun bind(userEntity: UserEntity)
}