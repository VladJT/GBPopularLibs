package jt.projects.gbpopularlibs.core.nav

import android.os.Bundle
import com.github.terrakok.cicerone.Screen

interface IScreens {
    fun users(): Screen
    fun countersMvp(): Screen
    fun userCard(bundle: Bundle): Screen
}