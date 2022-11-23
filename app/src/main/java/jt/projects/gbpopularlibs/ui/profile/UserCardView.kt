package jt.projects.gbpopularlibs.ui.profile

import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType

@StateStrategyType(AddToEndSingleStrategy::class)
interface UserCardView : MvpView {
    fun showLogin(text: String)
}