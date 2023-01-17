package jt.projects.gbpopularlibs.di

import dagger.Subcomponent
import jt.projects.gbpopularlibs.presenter.profile.UserProfilePresenter
import javax.inject.Scope

/**
 * В текущем виде практически всё предоставляемое посредством Dagger существует столько, сколько
приложение. Однако некоторые зависимости необходимы в течение ограниченного времени.
Например, IGithubRepositoriesRepo используется, только пока жив экран пользователя, а после ухода
с него больше не требуется.
Таким образом, мы привязываем срок жизни подобных вещей к сроку жизни экранов, на которых они
используются. Сделаем это посредством Subcomponent и Scope.
 */
@Scope
@Retention(AnnotationRetention.RUNTIME)
annotation class UserProfileScope

interface IUserProfileScopeContainer {
    fun userProfileSCRelease()
}

@UserProfileScope
@Subcomponent(modules = [UserProfileModule::class])
interface UserProfileSubcomponent {
    fun inject(userProfilePresenter: UserProfilePresenter)
}
