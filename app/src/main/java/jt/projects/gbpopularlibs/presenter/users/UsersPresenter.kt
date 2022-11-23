package jt.projects.gbpopularlibs.presenter.users

import android.util.Log
import com.github.terrakok.cicerone.Router
import jt.projects.gbnasaapp.model.mars.UsersRepoRetrofitImpl
import jt.projects.gbpopularlibs.domain.entities.UserEntity
import jt.projects.gbpopularlibs.domain.interfaces.CommonCallback
import jt.projects.gbpopularlibs.domain.interfaces.UsersRepository
import jt.projects.gbpopularlibs.ui.main.AndroidScreens
import jt.projects.gbpopularlibs.ui.users.UserItemView
import jt.projects.gbpopularlibs.ui.users.UsersView
import moxy.MvpPresenter

/**
 *  формируем UsersPresenter для работы с UsersView и передав в него Router для навигации
 */
class UsersPresenter(val router: Router) : MvpPresenter<UsersView>() {

    //  val usersRepo: UsersRepository = UsersRepositoryLocalImpl()
    private val usersRepo: UsersRepository = UsersRepoRetrofitImpl()

    companion object {
        var currentUser = UserEntity("current user")
    }

    class UsersListPresenter : IUserListPresenter {
        val users = mutableListOf<UserEntity>()

        override var itemClickListener: ((UserItemView) -> Unit)? = null

        override fun getCount() = users.size

        override fun bindView(view: UserItemView) {
            val user = users[view.pos]
            view.bind(user)
        }
    }

    val usersListPresenter = UsersListPresenter()

    /***
     * открытие карточки пользователя
     */
    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.init()
        loadData()
        usersListPresenter.itemClickListener = { itemView ->
            Log.i("@@@", itemView.pos.toString())
            currentUser = usersListPresenter.users[itemView.pos]
            router.sendResult("USER_DATA", usersListPresenter.users[itemView.pos])
            router.navigateTo(AndroidScreens().userCard())
        }
    }

    private fun loadData() {
        viewState.showLoading(true)
        usersRepo.getUsers(object : CommonCallback<List<UserEntity>> {
            override fun onSuccess(data: List<UserEntity>) {
                usersListPresenter.users.addAll(data)
                viewState.updateList()
                viewState.showLoading(false)
            }

            override fun onFailure(e: Throwable) {
                e.message?.let { viewState.showInfo(it) }
                viewState.showLoading(false)
            }
        })
    }

    fun backPressed(): Boolean {
        router.exit()
        return true
    }
}