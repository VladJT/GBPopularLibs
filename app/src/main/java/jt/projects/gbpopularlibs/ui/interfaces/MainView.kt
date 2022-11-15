package jt.projects.gbpopularlibs.ui.interfaces

import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType

/**
init() — для первичной инициализации списка, который мы будем вызывать при присоединении View к Presenter
updateList() — для обновления содержимого списка.
 */
@StateStrategyType(AddToEndSingleStrategy::class)
interface MainView : MvpView {
    fun init()
    fun updateList()
}