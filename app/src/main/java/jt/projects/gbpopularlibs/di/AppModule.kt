package jt.projects.gbpopularlibs.di

import dagger.Module
import dagger.Provides
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import jt.projects.gbpopularlibs.App
import javax.inject.Singleton



//@Module
//@InstallIn(SingletonComponent::class)
//class AppModule //@AssistedInject constructor(@Assisted val app: App) {
//{
//    @Provides
//    fun app(): App {
//        return App.instance
//    }
//}