package jt.projects.gbpopularlibs.ui.cicerone

import com.github.terrakok.cicerone.Screen

interface IScreens {
    fun users(): Screen
    fun countersMvp(): Screen
  //  fun countersMvvm(): Screen
    fun settings(): Screen
    fun rxjava(): Screen
    fun userCard(): Screen
}