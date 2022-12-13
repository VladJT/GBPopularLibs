package jt.projects.gbpopularlibs.presenter.profile

import io.reactivex.rxjava3.disposables.Disposable
import jt.projects.gbpopularlibs.App
import jt.projects.gbpopularlibs.data.room.IUsersCache
import jt.projects.gbpopularlibs.data.room.UsersCacheRoomImpl
import jt.projects.gbpopularlibs.data.users.GHReposRepository
import jt.projects.gbpopularlibs.data.users.IUserGHReposRepository
import jt.projects.gbpopularlibs.domain.entities.UserEntity
import jt.projects.gbpopularlibs.domain.entities.UserGHRepo
import jt.projects.gbpopularlibs.ui.profile.UserProfileView
import jt.projects.gbpopularlibs.utils.INetworkStatus
import jt.projects.gbpopularlibs.utils.subscribeByDefault
import moxy.MvpPresenter

/**
 *  формируем UsersPresenter для работы с UsersView и передав в него Router для навигации
 */
class UserProfilePresenter(val userEntity: UserEntity) : MvpPresenter<UserProfileView>() {
    private val cacheSource: IUsersCache = UsersCacheRoomImpl(App.instance.getDatabase())
    private val networkStatus: INetworkStatus = App.instance.getNetworkStatus()
    private var disposable: Disposable? = null

    private val usersGHReposRepo: IUserGHReposRepository =
        GHReposRepository(networkStatus, cacheSource)

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        loadProfile()
    }

    fun loadProfile() {
        viewState.showLoading(true)
        viewState.showUserProfile(userEntity)
        disposable = usersGHReposRepo.getUserGHRepos(userEntity)
            .subscribeByDefault()
            .subscribe({ data ->
                onSuccess(data)
            }, { e ->
                onError(e)
            })
    }


    private fun onError(e: Throwable) {
        e.message?.let { viewState.showInfo(it) }
        viewState.showLoading(false)
    }

    private fun onSuccess(data: List<UserGHRepo>) {
        val sb = StringBuilder()
        data.forEach {
            sb.append("repo: ${it.name} forks: ${it.forksCount} [id:${it.id}]\n")
        }
        viewState.showUserRepos(sb.toString())
        viewState.showLoading(false)

    }

    fun backPressed(): Boolean {
        App.instance.router.exit()
        return true
    }

    fun clear() {
        disposable?.dispose()
    }
}