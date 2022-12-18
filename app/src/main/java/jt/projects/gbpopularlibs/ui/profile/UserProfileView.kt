package jt.projects.gbpopularlibs.ui.profile

import jt.projects.gbpopularlibs.domain.entities.UserEntity
import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType

@StateStrategyType(AddToEndSingleStrategy::class)
interface UserProfileView : MvpView {
    fun init()
    fun updateList()

    fun showLoading(isLoading: Boolean)//show loading frame
    fun showInfo(text: String)//snackbar or toast

    fun showUserProfile(user: UserEntity)
}