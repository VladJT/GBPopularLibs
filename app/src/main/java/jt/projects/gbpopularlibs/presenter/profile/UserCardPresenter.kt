package jt.projects.gbpopularlibs.presenter.profile

import com.github.terrakok.cicerone.ResultListener
import jt.projects.gbpopularlibs.App
import jt.projects.gbpopularlibs.domain.entities.UserEntity
import jt.projects.gbpopularlibs.presenter.users.UsersPresenter
import jt.projects.gbpopularlibs.ui.profile.UserCardView
import moxy.MvpPresenter

/**
 *  формируем UsersPresenter для работы с UsersView и передав в него Router для навигации
 */
class UserCardPresenter() : MvpPresenter<UserCardView>() {

    init {
        App.instance.router.setResultListener("USER_DATA", ResultListener { user ->
            (user as? UserEntity)?.let {
                // ПОЧЕМУ ТО НЕ РАБОТАЕТ =(
                viewState.showUserProfile(it)
            }
        })
    }

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.showUserProfile(UsersPresenter.currentUser)
    }

    fun backPressed(): Boolean {
        App.instance.router.exit()
        return true
    }
}