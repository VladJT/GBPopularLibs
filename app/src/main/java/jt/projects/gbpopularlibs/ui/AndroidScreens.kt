package jt.projects.gbpopularlibs.ui

import com.github.terrakok.cicerone.Screen
import com.github.terrakok.cicerone.androidx.FragmentScreen
import jt.projects.gbpopularlibs.ui.interfaces.IScreens
import jt.projects.gbpopularlibs.utils.COUNTERS_SCREEN
import jt.projects.gbpopularlibs.utils.SETTINGS_SCREEN
import jt.projects.gbpopularlibs.utils.USERS_SCREEN
import jt.projects.gbpopularlibs.utils.USER_CARD_SCREEN

/**
 * Классы Screen и FragmentScreen — часть Cicerone, причём второй — наследник первого. В его
конструктор мы передаём функтор, создающий фрагмент. Такой фрагмент представляет собой экран.
В дальнейшем функтор вызывается внутри навигатора при получении навигационных команд
 */
class AndroidScreens : IScreens {
    override fun users(): Screen =
        FragmentScreen(USERS_SCREEN) { UsersFragment.newInstance() }

    override fun counters(): Screen =
        FragmentScreen(COUNTERS_SCREEN) { CounterFragment.newInstance() }

    override fun settings(): Screen =
        FragmentScreen(SETTINGS_SCREEN) { SettingsFragment.newInstance() }

    override fun userCard(): Screen =
        FragmentScreen(USER_CARD_SCREEN) { UserCardFragment.newInstance() }
}