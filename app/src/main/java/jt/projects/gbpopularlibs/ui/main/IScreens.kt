package jt.projects.gbpopularlibs.ui.main

import com.github.terrakok.cicerone.Screen

interface IScreens {
    fun users(): Screen
    fun counters(): Screen
    fun settings(): Screen
    fun userCard(): Screen
}