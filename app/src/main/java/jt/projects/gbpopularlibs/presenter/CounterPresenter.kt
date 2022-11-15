package jt.projects.gbpopularlibs.presenter

import jt.projects.gbpopularlibs.model.CountersModel
import jt.projects.gbpopularlibs.ui.CounterView


class CounterPresenter(private val view: CounterView) {
    private val model = CountersModel()

    fun counter1Click() {
        view.setButton1Text(model.next(0).toString())
    }

    fun counter2Click() {
        view.setButton2Text(model.next(1).toString())
    }

    fun counter3Click() {
        view.setButton3Text(model.next(2).toString())
    }
}