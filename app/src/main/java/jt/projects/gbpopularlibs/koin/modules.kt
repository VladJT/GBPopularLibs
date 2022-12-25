package jt.projects.gbpopularlibs.koin

import android.content.Context
import android.net.ConnectivityManager
import androidx.room.Room
import com.github.terrakok.cicerone.Cicerone
import com.github.terrakok.cicerone.NavigatorHolder
import com.github.terrakok.cicerone.Router
import jt.projects.gbpopularlibs.App
import jt.projects.gbpopularlibs.BuildConfig
import jt.projects.gbpopularlibs.core.nav.AndroidScreens
import jt.projects.gbpopularlibs.core.nav.IScreens
import jt.projects.gbpopularlibs.core.utils.INetworkStatus
import jt.projects.gbpopularlibs.core.utils.NetworkStatus
import jt.projects.gbpopularlibs.data.ghrepos.GhRepoRepositoryRetrofitImpl
import jt.projects.gbpopularlibs.data.ghrepos.GhReposCacheRoomImpl
import jt.projects.gbpopularlibs.data.ghrepos.IGhReposCache
import jt.projects.gbpopularlibs.data.ghrepos.IGhReposRepository
import jt.projects.gbpopularlibs.data.retrofit.GithubAPI
import jt.projects.gbpopularlibs.data.retrofit.RetrofitDataSourceImpl
import jt.projects.gbpopularlibs.data.room.AppDatabase
import jt.projects.gbpopularlibs.data.users.IUsersCache
import jt.projects.gbpopularlibs.data.users.IUsersRepository
import jt.projects.gbpopularlibs.data.users.UsersCacheRoomImpl
import jt.projects.gbpopularlibs.data.users.UsersRepositoryRetrofitImpl
import jt.projects.gbpopularlibs.presenter.main.MainPresenter
import jt.projects.gbpopularlibs.presenter.profile.UserProfilePresenter
import jt.projects.gbpopularlibs.presenter.users.UsersPresenter
import jt.projects.gbpopularlibs.viewmodel.CounterViewModel
import org.koin.android.ext.koin.androidApplication
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

/**
Singleton -- single<UserRepository> { UserRepositoryImpl() }
создание каждый раз нового экземпляра -- factory { MyPresenter(get()) }
 */
val roomModule = module {
    single<AppDatabase> {
        Room.databaseBuilder(
            androidApplication(),
            AppDatabase::class.java,
            BuildConfig.DB_NAME
        ).build()
    }

    single<IUsersCache> { UsersCacheRoomImpl(get()) }
    single<IGhReposCache> { GhReposCacheRoomImpl(get()) }

}

val retrofitModule = module {
    single<GithubAPI> { RetrofitDataSourceImpl().getApi() }

    single<IUsersRepository> { UsersRepositoryRetrofitImpl(get(), get(), get()) }
    single<IGhReposRepository> { GhRepoRepositoryRetrofitImpl(get(), get(), get()) }
}

val networkModule = module {
    single<INetworkStatus> { NetworkStatus(androidContext()) }
//    single<ConnectivityManager> {
//        androidApplication()
//            .getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
//    }
}


val navModule = module {
    val cicerone: Cicerone<Router> = Cicerone.create()

    single<Cicerone<Router>> { cicerone }
    single<NavigatorHolder> { cicerone.getNavigatorHolder() }
    single<Router> { cicerone.router }
    single<IScreens> { AndroidScreens() }
}

val appModule = module {
    single<App> { App.instance }
}

val vmModule = module {
    viewModel<CounterViewModel> { CounterViewModel(router = get()) }
}
