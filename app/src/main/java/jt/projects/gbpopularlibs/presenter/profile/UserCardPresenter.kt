package jt.projects.gbpopularlibs.presenter.profile

import jt.projects.gbpopularlibs.App
import jt.projects.gbpopularlibs.domain.entities.UserEntity
import jt.projects.gbpopularlibs.ui.profile.UserProfileView
import moxy.MvpPresenter

/**
 *  формируем UsersPresenter для работы с UsersView и передав в него Router для навигации
 */
class UserCardPresenter(val userEntity: UserEntity) : MvpPresenter<UserProfileView>() {

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        showProfile()
    }

    fun showProfile(){
        viewState.showUserProfile(userEntity)
    }

    fun backPressed(): Boolean {
        App.instance.router.exit()
        return true
    }
}