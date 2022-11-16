package jt.projects.gbpopularlibs.ui

import com.github.terrakok.cicerone.Screen
import com.github.terrakok.cicerone.androidx.FragmentScreen
import jt.projects.gbpopularlibs.ui.interfaces.IScreens

/**
 * Классы Screen и FragmentScreen — часть Cicerone, причём второй — наследник первого. В его
конструктор мы передаём функтор, создающий фрагмент. Такой фрагмент представляет собой экран.
В дальнейшем функтор вызывается внутри навигатора при получении навигационных команд
 */
class AndroidScreens : IScreens {
    override fun users(): Screen {
        return FragmentScreen { UsersFragment.newInstance() }
    }

}