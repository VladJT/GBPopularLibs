package jt.projects.gbpopularlibs.ui.profile

import jt.projects.gbpopularlibs.domain.entities.UserEntity
import jt.projects.gbpopularlibs.domain.entities.UserGHRepo
import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType

@StateStrategyType(AddToEndSingleStrategy::class)
interface UserProfileView : MvpView {
    fun showUserProfile(user: UserEntity)
    fun showUserRepos(text: String)

    fun showLoading(isLoading: Boolean)//show loading frame
    fun showInfo(text: String)//snackbar or toast
}