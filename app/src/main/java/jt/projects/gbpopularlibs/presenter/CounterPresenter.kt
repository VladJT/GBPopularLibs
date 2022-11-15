package jt.projects.gbpopularlibs.presenter

import jt.projects.gbpopularlibs.model.CountersModel
import jt.projects.gbpopularlibs.ui.CounterView
import moxy.MvpPresenter

class CounterPresenter() : MvpPresenter<CounterView>() {
    val model = CountersModel()

    override fun onFirstViewAttach() {

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
}