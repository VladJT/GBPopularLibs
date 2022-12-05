package jt.projects.gbpopularlibs.presenter.main

import android.content.Context
import android.content.Intent
import androidx.core.content.ContextCompat.startActivity
import androidx.fragment.app.FragmentManager
import com.github.terrakok.cicerone.Screen
import jt.projects.gbpopularlibs.App
import jt.projects.gbpopularlibs.ui.cicerone.AndroidScreens
import jt.projects.gbpopularlibs.ui.cicerone.IScreens
import jt.projects.gbpopularlibs.ui.counters_mvvm.CounterMVVMActivity
import jt.projects.gbpopularlibs.ui.main.MainView
import moxy.MvpPresenter

class MainPresenter(val fragmentManager: FragmentManager) :
    MvpPresenter<MainView>() {

    val screens: IScreens = AndroidScreens()

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

    fun showUsers(){
        showScreen(screens.users())
    }

    fun showCountersMvp(){
        showScreen(screens.countersMvp())
    }

    fun showRxJava(){
        showScreen(screens.rxjava())
    }

    fun showSettings(){
        showScreen(screens.settings())
    }

    fun backClicked() {
        App.instance.router.exit()
    }


}
