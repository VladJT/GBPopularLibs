package jt.projects.gbpopularlibs.presenter.users

import android.os.Bundle
import io.reactivex.rxjava3.disposables.CompositeDisposable
import jt.projects.gbpopularlibs.App
import jt.projects.gbpopularlibs.core.nav.AndroidScreens
import jt.projects.gbpopularlibs.core.utils.*
import jt.projects.gbpopularlibs.data.room.IUsersCache
import jt.projects.gbpopularlibs.data.room.UsersCacheRoomImpl
import jt.projects.gbpopularlibs.data.users.IUsersRepository
import jt.projects.gbpopularlibs.data.users.UsersRepositoryNetworkImpl
import jt.projects.gbpopularlibs.domain.entities.UserEntity
import jt.projects.gbpopularlibs.ui.users.UserItemView
import jt.projects.gbpopularlibs.ui.users.UsersView
import moxy.MvpPresenter


/**
 *  формируем UsersPresenter для работы с UsersView и передав в него Router для навигации
 */
class UsersPresenter : MvpPresenter<UsersView>() {

    private val cacheSource: IUsersCache = UsersCacheRoomImpl(App.instance.getDatabase())
    private val networkStatus: INetworkStatus = App.instance.getNetworkStatus()
    private val compositeDisposable = CompositeDisposable()

    private val usersRepo: IUsersRepository = //UsersRepoLocalImpl()
        UsersRepositoryNetworkImpl(networkStatus, cacheSource)

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
            .subscribeByDefault()
            .subscribe({ data ->
                onSuccess(data)
            }, { e ->
                onError(e)
            })
            .disposeBy(compositeDisposable)
    }

    private fun onError(e: Throwable) {
        e.message?.let { viewState.showInfo(it.addTime()) }
        viewState.showLoading(false)
    }

    private fun onSuccess(data: List<UserEntity>) {
        usersListPresenter.users.clear()
        usersListPresenter.users.addAll(data)
        viewState.updateList()
        viewState.showLoading(false)
        viewState.showInfo("User list успешно загружен".addTime())
    }

    fun backPressed(): Boolean {
        App.instance.router.exit()
        return true
    }

    fun clear() {
        compositeDisposable.dispose()
    }

}