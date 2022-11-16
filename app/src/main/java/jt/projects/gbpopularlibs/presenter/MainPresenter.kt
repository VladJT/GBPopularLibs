package jt.projects.gbpopularlibs.presenter

import com.github.terrakok.cicerone.Router
import jt.projects.gbpopularlibs.ui.interfaces.IScreens
import jt.projects.gbpopularlibs.ui.interfaces.MainView
import moxy.MvpPresenter

class MainPresenter(val router: Router, val screens: IScreens) :
    MvpPresenter<MainView>() {
    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        router.replaceScreen(screens.users())
    }

    fun backClicked() {
        router.exit()
    }
}
