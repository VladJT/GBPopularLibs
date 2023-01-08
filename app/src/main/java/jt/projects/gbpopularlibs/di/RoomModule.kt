package jt.projects.gbpopularlibs.di

import androidx.room.Room
import dagger.Module
import dagger.Provides
import jt.projects.gbpopularlibs.App
import jt.projects.gbpopularlibs.BuildConfig
import jt.projects.gbpopularlibs.data.room.AppDatabase
import javax.inject.Singleton

@Module
class RoomModule {
    /**
     * ROOM DATABASE
     */
    @Singleton
    @Provides
    fun database(app: App): AppDatabase =
        Room.databaseBuilder(app, AppDatabase::class.java, BuildConfig.DB_NAME)
            .fallbackToDestructiveMigration()
            .build()
}