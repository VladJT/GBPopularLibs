package jt.projects.gbpopularlibs.presenter

import android.util.Log
import com.github.terrakok.cicerone.Router
import jt.projects.gbpopularlibs.App
import jt.projects.gbpopularlibs.model.GithubUser
import jt.projects.gbpopularlibs.model.interfaces.GithubUsersRepository
import jt.projects.gbpopularlibs.presenter.interfaces.IUserListPresenter
import jt.projects.gbpopularlibs.ui.AndroidScreens
import jt.projects.gbpopularlibs.ui.interfaces.UserItemView
import jt.projects.gbpopularlibs.ui.interfaces.UsersView
import moxy.MvpPresenter

/**
 *  формируем UsersPresenter для работы с UsersView и передав в него Router для навигации
 */
class UsersPresenter(val usersRepo: GithubUsersRepository, val router: Router) :
    MvpPresenter<UsersView>() {

    class UsersListPresenter : IUserListPresenter {
        val users = mutableListOf<GithubUser>()

        override var itemClickListener: ((UserItemView) -> Unit)? = null

        override fun getCount() = users.size

        override fun bindView(view: UserItemView) {
            val user = users[view.pos]
            view.setLogin(user.login)
        }
    }

    val usersListPresenter = UsersListPresenter()

    /***
     * TODO !!!!!!!!!!!!!!!!!
     */
    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.init()
        loadData()
        usersListPresenter.itemClickListener = { itemView ->
            //TODO
            Log.i("@@@", itemView.pos.toString())
            router.sendResult("USER_DATA", usersListPresenter.users[itemView.pos])
            App.LOGIN_GLOBAL =  usersListPresenter.users[itemView.pos].login
            router.navigateTo(AndroidScreens().userCard())
        }
    }

    fun loadData() {
        usersListPresenter.users.addAll(usersRepo.getUsers())
        viewState.updateList()
    }

    fun backPressed(): Boolean {
        router.exit()
        return true
    }
}