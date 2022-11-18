package jt.projects.gbpopularlibs.presenter

import com.github.terrakok.cicerone.ResultListener
import com.github.terrakok.cicerone.Router
import jt.projects.gbpopularlibs.model.GithubUser
import jt.projects.gbpopularlibs.ui.interfaces.UserCardView
import moxy.MvpPresenter

/**
 *  формируем UsersPresenter для работы с UsersView и передав в него Router для навигации
 */
class UserCardPresenter(val router: Router) :
    MvpPresenter<UserCardView>() {

    init {
        router.setResultListener("USER_DATA", ResultListener { user ->
            (user as? GithubUser)?.let {
                // ПОЧЕМУ ТО НЕ РАБОТАЕТ =(
                viewState.showLogin(it.login + "@@@")
            }
        })
    }

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.showLogin(UsersPresenter.currentUser.login)
    }

    fun backPressed(): Boolean {
        router.exit()
        return true
    }
}