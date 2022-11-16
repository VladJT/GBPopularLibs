package jt.projects.gbpopularlibs.ui.interfaces

import com.github.terrakok.cicerone.Screen

interface IScreens {
    fun users(): Screen
    fun counters(): Screen
    fun settings(): Screen
    fun userCard(): Screen
}