package jt.projects.gbpopularlibs.presenter.counters

import com.github.terrakok.cicerone.Router
import jt.projects.gbpopularlibs.App
import jt.projects.gbpopularlibs.data.counters.CountersRepository
import jt.projects.gbpopularlibs.ui.counters_mvp.CounterView
import moxy.MvpPresenter
import javax.inject.Inject

class CounterPresenter() : MvpPresenter<CounterView>() {
    private val model = CountersRepository()

    @Inject
    lateinit var router: Router

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        App.instance.appComponent.inject(this)
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