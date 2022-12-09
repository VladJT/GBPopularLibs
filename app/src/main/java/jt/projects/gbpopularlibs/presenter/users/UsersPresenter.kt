package jt.projects.gbpopularlibs.presenter.users

import android.os.Bundle
import io.reactivex.rxjava3.core.Scheduler
import jt.projects.gbpopularlibs.App
import jt.projects.gbpopularlibs.data.users.IUserRepository
import jt.projects.gbpopularlibs.data.users.UsersRepositoryImpl
import jt.projects.gbpopularlibs.domain.entities.UserEntity
import jt.projects.gbpopularlibs.ui.main.AndroidScreens
import jt.projects.gbpopularlibs.ui.users.UserItemView
import jt.projects.gbpopularlibs.ui.users.UsersView
import jt.projects.gbpopularlibs.utils.USER_ENTITY_BUNDLE_KEY
import moxy.MvpPresenter


/**
 *  формируем UsersPresenter для работы с UsersView и передав в него Router для навигации
 */
class UsersPresenter(private val uiScheduler: Scheduler) : MvpPresenter<UsersView>() {

    //  val usersRepo: UsersRepository = UsersRepositoryLocalImpl()
    private val usersRepo: IUserRepository =
        UsersRepositoryImpl(App.instance.getNetworkStatus(), App.instance.getDatabase())

    class UsersListPresenter : IUserListPresenter {
        var users = mutableListOf<UserEntity>()

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
            val currentUser = usersListPresenter.users[itemView.pos]
            val bundle = Bundle()
            bundle.putParcelable(USER_ENTITY_BUNDLE_KEY, currentUser)
            App.instance.router.navigateTo(AndroidScreens().userCard(bundle))
        }
    }

    private fun loadData() {
        viewState.showLoading(true)

        usersRepo.getUsers()
            .observeOn(uiScheduler)
            .subscribe({ data ->
                usersListPresenter.users.clear()
                usersListPresenter.users.addAll(data)
                viewState.updateList()
                viewState.showLoading(false)
            }, { e ->
                e.message?.let { viewState.showInfo(it) }
                viewState.showLoading(false)
            })
    }

    fun backPressed(): Boolean {
        App.instance.router.exit()
        return true
    }
}