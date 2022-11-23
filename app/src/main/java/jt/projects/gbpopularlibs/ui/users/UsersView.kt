package jt.projects.gbpopularlibs.ui.users

import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType

/**
init() — для первичной инициализации списка, который мы будем вызывать при присоединении View к Presenter
updateList() — для обновления содержимого списка.
 */
@StateStrategyType(AddToEndSingleStrategy::class)
interface UsersView : MvpView {
    fun init()
    fun updateList()
    fun showLoading(isLoading:Boolean)
    fun showInfo(text: String)
}