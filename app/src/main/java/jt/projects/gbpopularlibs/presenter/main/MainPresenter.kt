package jt.projects.gbpopularlibs.presenter.main

import androidx.fragment.app.FragmentManager
import com.github.terrakok.cicerone.Router
import com.github.terrakok.cicerone.Screen
import jt.projects.gbpopularlibs.core.nav.IScreens
import jt.projects.gbpopularlibs.ui.main.MainView
import moxy.MvpPresenter

class MainPresenter(private val router: Router, private val screens: IScreens) :
    MvpPresenter<MainView>() {


    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        router.replaceScreen(screens.users())
    }

    fun showUsers() {
        showScreen(screens.users())
    }

    fun showCountersMvp() {
        showScreen(screens.countersMvp())
    }

    fun backClicked() {
        router.exit()
    }

    private fun showScreen(screen: Screen) {
       // val f = fragmentManager.findFragmentByTag(screen.screenKey)
    //    if (f == null) {
            router.navigateTo(screen, true)
     //   } else router.backTo(screen)
    }
}
