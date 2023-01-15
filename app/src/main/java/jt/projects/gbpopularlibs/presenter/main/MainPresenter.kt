package jt.projects.gbpopularlibs.presenter.main

import androidx.fragment.app.FragmentManager
import com.github.terrakok.cicerone.Router
import com.github.terrakok.cicerone.Screen
import jt.projects.gbpopularlibs.core.nav.IScreens
import jt.projects.gbpopularlibs.core.utils.INetworkStatus
import jt.projects.gbpopularlibs.core.utils.addTime
import jt.projects.gbpopularlibs.ui.main.MainView
import moxy.MvpPresenter
import javax.inject.Inject

class MainPresenter(private val fragmentManager: FragmentManager) :
    MvpPresenter<MainView>() {

    @Inject
    lateinit var router: Router

    @Inject
    lateinit var screens: IScreens

    @Inject
    lateinit var networkStatus: INetworkStatus

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        router.replaceScreen(screens.users())

        networkStatus.isOnline().subscribe() {
            viewState.printLog("⚡ Internet available: $it".addTime())
        }
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
        val f = fragmentManager.findFragmentByTag(screen.screenKey)
        if (f == null) {
            router.navigateTo(screen, true)
        } else router.backTo(screen)
    }
}
