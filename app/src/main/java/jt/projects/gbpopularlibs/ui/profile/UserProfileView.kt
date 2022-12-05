package jt.projects.gbpopularlibs.ui.profile

import jt.projects.gbpopularlibs.domain.entities.UserEntity
import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType

@StateStrategyType(AddToEndSingleStrategy::class)
interface UserProfileView : MvpView {
    fun showUserProfile(user: UserEntity)
}