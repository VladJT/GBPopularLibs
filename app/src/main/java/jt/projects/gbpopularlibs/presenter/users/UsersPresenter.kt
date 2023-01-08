package jt.projects.gbpopularlibs.presenter.users

import android.os.Bundle
import android.util.Log
import com.github.terrakok.cicerone.Router
import io.reactivex.rxjava3.disposables.CompositeDisposable
import jt.projects.gbpopularlibs.core.nav.IScreens
import jt.projects.gbpopularlibs.core.utils.*
import jt.projects.gbpopularlibs.data.users.IUsersRepository
import jt.projects.gbpopularlibs.di.IUsersScopeContainer
import jt.projects.gbpopularlibs.domain.entities.UserEntity
import jt.projects.gbpopularlibs.ui.users.UserItemView
import jt.projects.gbpopularlibs.ui.users.UsersView
import moxy.MvpPresenter
import javax.inject.Inject


/**
 *  формируем UsersPresenter для работы с UsersView и передав в него Router для навигации
 */
class UsersPresenter : MvpPresenter<UsersView>() {
    @Inject
    lateinit var usersRepo: IUsersRepository

    @Inject
    lateinit var router: Router

    @Inject
    lateinit var screens: IScreens

    @Inject
    lateinit var userScopeContainer: IUsersScopeContainer

    private val compositeDisposable = CompositeDisposable()

    class UsersListPresenter : IUserListPresenter {
        var users = mutableListOf<UserEntity>()

        override var itemClickListener: ((UserItemView) -> Unit)? = null

        override fun getCount() = users.size
        override fun getData(): MutableList<UserEntity> = users

        override fun bindView(view: UserItemView) {
            val user = users[view.pos]
            view.bind(user)
        }
    }

    val usersListPresenter = UsersListPresenter()


    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.init()
        loadData()

        /** открытие карточки пользователя */
        usersListPresenter.itemClickListener = { itemView ->
            val currentUser = usersListPresenter.users[itemView.pos]
            val bundle = Bundle()
            bundle.putParcelable(USER_ENTITY_BUNDLE_KEY, currentUser)
            router.navigateTo(screens.userCard(bundle))
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
        viewState.showInfo("Список пользователей (${data.size}) загружен".addTime())
    }

    fun backPressed(): Boolean {
        router.exit()
        return true
    }

    fun clear() {
        compositeDisposable.dispose()
    }

    override fun onDestroy() {
        userScopeContainer.usersSCRelease()
        super.onDestroy()
    }
}