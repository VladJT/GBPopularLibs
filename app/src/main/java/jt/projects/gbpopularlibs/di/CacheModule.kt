package jt.projects.gbpopularlibs.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import jt.projects.gbpopularlibs.App
import jt.projects.gbpopularlibs.BuildConfig
import jt.projects.gbpopularlibs.data.ghrepos.GhReposCacheRoomImpl
import jt.projects.gbpopularlibs.data.ghrepos.IGhReposCache
import jt.projects.gbpopularlibs.data.room.AppDatabase
import jt.projects.gbpopularlibs.data.users.IUsersCache
import jt.projects.gbpopularlibs.data.users.UsersCacheRoomImpl
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class CacheModule {
    /**
     * ROOM DATABASE
     */
    @Singleton
    @Provides
    fun database(@ApplicationContext appContext: Context): AppDatabase = Room.databaseBuilder(
        appContext,
        AppDatabase::class.java, BuildConfig.DB_NAME
    ).build()


    @Singleton
    @Provides
    fun usersCache(database: AppDatabase): IUsersCache = UsersCacheRoomImpl(database)

    @Singleton
    @Provides
    fun cacheSource(database: AppDatabase): IGhReposCache = GhReposCacheRoomImpl(database)
}