package jt.projects.gbpopularlibs.ui.cicerone

import com.github.terrakok.cicerone.Screen
import com.github.terrakok.cicerone.androidx.FragmentScreen
import jt.projects.gbpopularlibs.domain.entities.UserEntity
import jt.projects.gbpopularlibs.ui.counters_mvp.CounterMVPFragment
import jt.projects.gbpopularlibs.ui.counters_mvvm.CounterMVVMActivity
import jt.projects.gbpopularlibs.ui.profile.UserCardFragment
import jt.projects.gbpopularlibs.ui.rxjava.RxJavaFragment
import jt.projects.gbpopularlibs.ui.settings.SettingsFragment
import jt.projects.gbpopularlibs.ui.users.UsersFragment
import jt.projects.gbpopularlibs.utils.*

/**
 * Классы Screen и FragmentScreen — часть Cicerone, причём второй — наследник первого. В его
конструктор мы передаём функтор, создающий фрагмент. Такой фрагмент представляет собой экран.
В дальнейшем функтор вызывается внутри навигатора при получении навигационных команд
 */
class AndroidScreens : IScreens {
    override fun users(): Screen =
        FragmentScreen(USERS_SCREEN) { UsersFragment.newInstance() }

    override fun countersMvp(): Screen =
        FragmentScreen(COUNTERS_MVP_SCREEN) { CounterMVPFragment.newInstance() }

//    override fun countersMvvm(): Screen =
//        FragmentScreen(COUNTERS_MVVM_SCREEN) { CounterMVVMActivity.newInstance() }

    override fun rxjava(): Screen =
        FragmentScreen(RXJAVA_SCREEN) { RxJavaFragment.newInstance() }

    override fun settings(): Screen =
        FragmentScreen(SETTINGS_SCREEN) { SettingsFragment.newInstance() }

    override fun userCard(user: UserEntity): Screen =
        FragmentScreen(USER_CARD_SCREEN) { UserCardFragment.newInstance(user) }
}