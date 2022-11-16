package jt.projects.gbpopularlibs.presenter

import com.github.terrakok.cicerone.Router
import jt.projects.gbpopularlibs.model.CountersModel
import jt.projects.gbpopularlibs.ui.interfaces.CounterView
import moxy.MvpPresenter

class CounterPresenter(val router: Router) : MvpPresenter<CounterView>() {
    val model = CountersModel()

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
    }

    fun counter1Click() {
        val nextValue = model.next(0)
        viewState.setButton1Text(nextValue.toString())
    }

    fun counter2Click() {
        val nextValue = model.next(1)
        viewState.setButton2Text(nextValue.toString())
    }

    fun counter3Click() {
        val nextValue = model.next(2)
        viewState.setButton3Text(nextValue.toString())
    }

    fun backPressed(): Boolean {
        router.exit()
        return true
    }
}