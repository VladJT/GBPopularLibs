package jt.projects.gbpopularlibs.presenter

import com.github.terrakok.cicerone.ResultListener
import com.github.terrakok.cicerone.Router
import jt.projects.gbpopularlibs.App
import jt.projects.gbpopularlibs.model.GithubUser
import jt.projects.gbpopularlibs.ui.interfaces.UserCardView
import moxy.MvpPresenter

/**
 *  формируем UsersPresenter для работы с UsersView и передав в него Router для навигации
 */
class UserCardPresenter(val router: Router) :
    MvpPresenter<UserCardView>() {


    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        router.setResultListener("USER_DATA", ResultListener { user ->
            (user as? GithubUser)?.let {
           //     viewState.showLogin(it.login)
            }
        })
        val user = App.LOGIN_GLOBAL

        viewState.showLogin(user)
    }


    fun setTestLogin(){
        viewState.showLogin("TEST LOGIN")
    }

    fun backPressed(): Boolean {
        router.exit()
        return true
    }
}