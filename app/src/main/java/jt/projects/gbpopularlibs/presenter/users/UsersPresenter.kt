package jt.projects.gbpopularlibs.presenter.users

import android.util.Log
import io.reactivex.rxjava3.core.Scheduler
import jt.projects.gbnasaapp.model.mars.UsersRepoRetrofitImpl
import jt.projects.gbpopularlibs.App
import jt.projects.gbpopularlibs.domain.entities.UserEntity
import jt.projects.gbpopularlibs.domain.interfaces.UsersRepository
import jt.projects.gbpopularlibs.ui.cicerone.AndroidScreens
import jt.projects.gbpopularlibs.ui.users.UserItemView
import jt.projects.gbpopularlibs.ui.users.UsersView
import moxy.MvpPresenter

/**
 *  формируем UsersPresenter для работы с UsersView и передав в него Router для навигации
 */
class UsersPresenter(private val uiScheduler: Scheduler) : MvpPresenter<UsersView>() {

    //  val usersRepo: UsersRepository = UsersRepositoryLocalImpl()
    private val usersRepo: UsersRepository = UsersRepoRetrofitImpl()

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
            val currentUser = usersListPresenter.users[itemView.pos]
            App.instance.router.navigateTo(AndroidScreens().userCard(currentUser))
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


//        usersRepo.getUsers(object : CommonCallback<List<UserEntity>> {
//            override fun onSuccess(data: List<UserEntity>) {
//                usersListPresenter.users.addAll(data)
//                viewState.updateList()
//                viewState.showLoading(false)
//            }
//
//            override fun onFailure(e: Throwable) {
//                e.message?.let { viewState.showInfo(it) }
//                viewState.showLoading(false)
//            }
//        })
    }

    fun backPressed(): Boolean {
        App.instance.router.exit()
        return true
    }
}