package jt.projects.gbpopularlibs.di

import dagger.Component
import jt.projects.gbpopularlibs.core.utils.NetworkStatus
import jt.projects.gbpopularlibs.presenter.counters.CounterPresenter
import jt.projects.gbpopularlibs.presenter.main.MainPresenter
import jt.projects.gbpopularlibs.presenter.profile.UserProfilePresenter
import jt.projects.gbpopularlibs.presenter.users.UsersPresenter
import jt.projects.gbpopularlibs.ui.main.MainActivity
import jt.projects.gbpopularlibs.viewmodel.CounterViewModel
import javax.inject.Singleton


@Singleton
@Component(
    modules = [AppModule::class, CiceroneModule::class, NetworkModule::class, CacheModule::class,
        RepoModule::class, ApiModule::class]
)
interface AppComponent {
    fun inject(mainActivity: MainActivity)
    fun inject(mainPresenter: MainPresenter)
    fun inject(usersPresenter: UsersPresenter)
    fun inject(userProfilePresenter: UserProfilePresenter)
    fun inject(counterPresenter: CounterPresenter)
    fun inject(counterViewModel: CounterViewModel)
    fun inject(networkStatus: NetworkStatus)
}