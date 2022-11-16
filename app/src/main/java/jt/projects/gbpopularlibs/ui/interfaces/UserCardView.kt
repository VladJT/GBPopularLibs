package jt.projects.gbpopularlibs.ui.interfaces

import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType


@StateStrategyType(AddToEndSingleStrategy::class)
interface UserCardView : MvpView {
    fun showLogin(text: String)
}