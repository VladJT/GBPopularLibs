package jt.projects.gbpopularlibs.data.room

import androidx.room.Database
import androidx.room.RoomDatabase
import jt.projects.gbpopularlibs.data.room.UserDao
import jt.projects.gbpopularlibs.data.room.UserGHRepoDao
import jt.projects.gbpopularlibs.domain.entities.UserEntity
import jt.projects.gbpopularlibs.domain.entities.UserGHRepo

//Это абстрактный класс, у которого должна быть определена аннотация @Database со всеми
//табличными объектами. В нашем случае это массив всего с одной таблицей. Версию БД указываем в
//version. Обязательно увеличиваем версию при изменении структуры БД. Параметр exportSchema
//указывает, создавать или нет файл в проекте, где будет храниться история версий БД. Это
//совершенно необязательно, но в коммерческих проектах — практика хорошая. В классе также
//требуется определить метод, возвращающий объект доступа к данным
// arrayOf(WeatherEntity::class, WeatherHistoryEntity::class)
@Database(entities = [UserEntity::class, UserGHRepo::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
    abstract fun repositoryDao() : UserGHRepoDao
}