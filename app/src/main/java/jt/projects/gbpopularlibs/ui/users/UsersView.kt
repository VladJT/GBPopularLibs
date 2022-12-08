package jt.projects.gbpopularlibs.ui.users

import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType



@StateStrategyType(AddToEndSingleStrategy::class)
interface UsersView : MvpView {
    fun init()//init() — для первичной инициализации списка, который мы будем вызывать при присоединении View к Presenter
    fun updateList()//updateList() — для обновления содержимого списка.
    fun showLoading(isLoading: Boolean)//show loading frame
    fun showInfo(text: String)//snackbar
}