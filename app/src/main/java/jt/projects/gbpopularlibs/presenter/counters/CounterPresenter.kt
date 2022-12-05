package jt.projects.gbpopularlibs.presenter.counters

import jt.projects.gbpopularlibs.App
import jt.projects.gbpopularlibs.data.counters.CountersData
import jt.projects.gbpopularlibs.ui.counters_mvp.CounterView
import moxy.MvpPresenter

class CounterPresenter() : MvpPresenter<CounterView>() {
    val model = CountersData()

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
        App.instance.router.exit()
        return true
    }
}