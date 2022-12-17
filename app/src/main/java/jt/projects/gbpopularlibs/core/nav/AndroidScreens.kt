package jt.projects.gbpopularlibs.core.nav

import android.os.Bundle
import com.github.terrakok.cicerone.Screen
import com.github.terrakok.cicerone.androidx.FragmentScreen
import jt.projects.gbpopularlibs.ui.counters_mvp.CounterMVPFragment
import jt.projects.gbpopularlibs.ui.profile.UserProfileFragment
import jt.projects.gbpopularlibs.ui.settings.SettingsFragment
import jt.projects.gbpopularlibs.ui.users.UsersFragment
import jt.projects.gbpopularlibs.core.utils.COUNTERS_MVP_SCREEN
import jt.projects.gbpopularlibs.core.utils.SETTINGS_SCREEN
import jt.projects.gbpopularlibs.core.utils.USERS_SCREEN
import jt.projects.gbpopularlibs.core.utils.USER_CARD_SCREEN

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

    override fun settings(): Screen =
        FragmentScreen(SETTINGS_SCREEN) { SettingsFragment.newInstance() }

    override fun userCard(bundle: Bundle): Screen =
        FragmentScreen(USER_CARD_SCREEN) {
            UserProfileFragment.newInstance().apply { arguments = bundle }
        }
}