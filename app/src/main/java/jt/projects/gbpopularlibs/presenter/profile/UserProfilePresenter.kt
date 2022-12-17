package jt.projects.gbpopularlibs.presenter.profile

import io.reactivex.rxjava3.disposables.Disposable
import jt.projects.gbpopularlibs.App
import jt.projects.gbpopularlibs.data.room.GhReposCacheRoomImpl
import jt.projects.gbpopularlibs.data.room.IGhReposCache
import jt.projects.gbpopularlibs.data.room.IUsersCache
import jt.projects.gbpopularlibs.data.room.UsersCacheRoomImpl
import jt.projects.gbpopularlibs.data.users.GhRepoRepositoryRetrofitImpl
import jt.projects.gbpopularlibs.data.users.IGhReposRepository
import jt.projects.gbpopularlibs.domain.entities.GhRepoEntity
import jt.projects.gbpopularlibs.domain.entities.UserEntity
import jt.projects.gbpopularlibs.ui.profile.UserProfileView
import jt.projects.gbpopularlibs.core.utils.INetworkStatus
import jt.projects.gbpopularlibs.core.utils.subscribeByDefault
import moxy.MvpPresenter

/**
 *  формируем UsersPresenter для работы с UsersView и передав в него Router для навигации
 */
class UserProfilePresenter(val userEntity: UserEntity) : MvpPresenter<UserProfileView>() {
    private val cacheSource: IGhReposCache = GhReposCacheRoomImpl(App.instance.getDatabase())
    private val networkStatus: INetworkStatus = App.instance.getNetworkStatus()
    private var disposable: Disposable? = null

    private val usersGHReposRepo: IGhReposRepository =
        GhRepoRepositoryRetrofitImpl(networkStatus, cacheSource)

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

    private fun onSuccess(data: List<GhRepoEntity>) {
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