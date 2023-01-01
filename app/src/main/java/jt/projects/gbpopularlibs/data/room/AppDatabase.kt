package jt.projects.gbpopularlibs.data.room

import androidx.room.Database
import androidx.room.RoomDatabase
import jt.projects.gbpopularlibs.domain.entities.GhRepoEntity
import jt.projects.gbpopularlibs.domain.entities.UserEntity


//Это абстрактный класс, у которого должна быть определена аннотация @Database со всеми
//табличными объектами. В нашем случае это массив всего с одной таблицей. Версию БД указываем в
//version. Обязательно увеличиваем версию при изменении структуры БД. Параметр exportSchema
//указывает, создавать или нет файл в проекте, где будет храниться история версий БД. Это
//совершенно необязательно, но в коммерческих проектах — практика хорошая. В классе также
//требуется определить метод, возвращающий объект доступа к данным
// arrayOf(WeatherEntity::class, WeatherHistoryEntity::class)
@Database(entities = [UserEntity::class, GhRepoEntity::class], version = 6)
abstract class AppDatabase : RoomDatabase() {
    abstract fun usersDao(): UsersDao
    abstract fun ghReposDao(): GhReposDao
}

