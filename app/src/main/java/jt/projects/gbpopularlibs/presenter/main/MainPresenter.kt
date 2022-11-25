package jt.projects.gbpopularlibs.presenter.main

import androidx.fragment.app.FragmentManager
import com.github.terrakok.cicerone.Screen
import jt.projects.gbpopularlibs.App
import jt.projects.gbpopularlibs.ui.cicerone.IScreens
import jt.projects.gbpopularlibs.ui.main.MainView
import moxy.MvpPresenter

class MainPresenter(val fragmentManager: FragmentManager, val screens: IScreens) :
    MvpPresenter<MainView>() {

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        App.instance.router.replaceScreen(screens.users())
    }

    fun showScreen(screen: Screen) {
        val f = fragmentManager.findFragmentByTag(screen.screenKey)
        if (f == null) {
            App.instance.router.navigateTo(screen, true)
        } else App.instance.router.backTo(screen)
    }

    fun backClicked() {
        App.instance.router.exit()
    }


}
