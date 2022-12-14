package jt.projects.gbpopularlibs.data.room

import androidx.room.*
import jt.projects.gbpopularlibs.domain.entities.UserEntity


/**
 *  Dao — это интерфейс, содержащий методы для взаимодействия с конкретной таблицей.
 */
@Dao
interface UsersDao {
    @Query("Select * from UserEntity")
    fun getAll(): List<UserEntity>

    @Query("select * from UserEntity where id in (:uids)")
    fun getAllById(uids: IntArray): List<UserEntity>

    @Query("SELECT * FROM UserEntity WHERE login LIKE :login LIMIT 1")
    fun findByLogin(login: String): UserEntity

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(user: UserEntity)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(vararg users: UserEntity)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(users: List<UserEntity>)


    @Update
    fun update(vararg users: UserEntity)

    @Delete
    fun delete(vararg user: UserEntity)
}