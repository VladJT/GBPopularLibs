package jt.projects.gbpopularlibs.di

import dagger.Subcomponent
import jt.projects.gbpopularlibs.presenter.users.UsersPresenter
import javax.inject.Scope

@Scope
@Retention(AnnotationRetention.RUNTIME)
annotation class UsersScope

interface IUsersScopeContainer {
    fun usersSCRelease()
}

@UsersScope
@Subcomponent(modules = [UsersModule::class])
interface UsersSubcomponent {
    fun userProfileSubcomponent(): UserProfileSubcomponent
    fun inject(usersPresenter: UsersPresenter)
}