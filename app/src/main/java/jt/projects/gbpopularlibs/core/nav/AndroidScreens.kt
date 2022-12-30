package jt.projects.gbpopularlibs.core.nav

import android.os.Bundle
import com.github.terrakok.cicerone.Screen
import com.github.terrakok.cicerone.androidx.FragmentScreen
import jt.projects.gbpopularlibs.core.utils.SCREENS
import jt.projects.gbpopularlibs.ui.counters_mvp.CounterMVPFragment
import jt.projects.gbpopularlibs.ui.profile.UserProfileFragment
import jt.projects.gbpopularlibs.ui.users.UsersFragment

/**
 * Классы Screen и FragmentScreen — часть Cicerone, причём второй — наследник первого. В его
конструктор мы передаём функтор, создающий фрагмент. Такой фрагмент представляет собой экран.
В дальнейшем функтор вызывается внутри навигатора при получении навигационных команд
 */
class AndroidScreens : IScreens {
    override fun users(): Screen =
        FragmentScreen(SCREENS.USERS.ID) { UsersFragment.newInstance() }

    override fun countersMvp(): Screen =
        FragmentScreen(SCREENS.COUNTERS_MVP.ID) { CounterMVPFragment.newInstance() }

    override fun userCard(bundle: Bundle): Screen =
        FragmentScreen(SCREENS.USER_CARD.ID) {
            UserProfileFragment.newInstance().apply { arguments = bundle }
        }
}