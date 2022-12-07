package jt.projects.gbpopularlibs.presenter.main

import androidx.fragment.app.FragmentManager
import com.github.terrakok.cicerone.Screen
import jt.projects.gbpopularlibs.App
import jt.projects.gbpopularlibs.ui.main.AndroidScreens
import jt.projects.gbpopularlibs.ui.main.IScreens
import jt.projects.gbpopularlibs.ui.main.MainView
import moxy.MvpPresenter

class MainPresenter(val fragmentManager: FragmentManager) :
    MvpPresenter<MainView>() {

    private val screens: IScreens = AndroidScreens()

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        App.instance.router.replaceScreen(screens.users())
    }

    fun showUsers() {
        showScreen(screens.users())
    }

    fun showCountersMvp() {
        showScreen(screens.countersMvp())
    }

    fun showRxJava() {
        showScreen(screens.rxjava())
    }

    fun showSettings() {
        showScreen(screens.settings())
    }

    fun backClicked() {
        App.instance.router.exit()
    }

    private fun showScreen(screen: Screen) {
        val f = fragmentManager.findFragmentByTag(screen.screenKey)
        if (f == null) {
            App.instance.router.navigateTo(screen, true)
        } else App.instance.router.backTo(screen)
    }
}
