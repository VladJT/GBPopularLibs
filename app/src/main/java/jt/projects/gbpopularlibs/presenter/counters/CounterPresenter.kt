package jt.projects.gbpopularlibs.presenter.counters

import com.github.terrakok.cicerone.Router
import jt.projects.gbpopularlibs.App
import jt.projects.gbpopularlibs.data.counters.CountersRepository
import jt.projects.gbpopularlibs.ui.counters_mvp.CounterView
import moxy.MvpPresenter

class CounterPresenter(private val router: Router) : MvpPresenter<CounterView>() {
    private val model = CountersRepository()


    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
    }

    fun counter1Click() {
        val nextValue = model.next(0)
        viewState.setFirstCounterText(nextValue.toString())
    }

    fun counter2Click() {
        val nextValue = model.next(1)
        viewState.setSecondCounterText(nextValue.toString())
    }

    fun counter3Click() {
        val nextValue = model.next(2)
        viewState.setThirdCounterText(nextValue.toString())
    }

    fun backPressed(): Boolean {
        router.exit()
        return true
    }
}