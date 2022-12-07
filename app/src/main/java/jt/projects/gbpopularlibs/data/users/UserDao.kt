package jt.projects.gbpopularlibs.data.users

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import jt.projects.gbpopularlibs.domain.entities.UserEntity

@Dao
interface UserDao {
    @Query("Select * from UserEntity")
    fun getAll(): List<UserEntity>

    @Query("select * from UserEntity where id in (:uids)")
    fun getAllById(uids: IntArray): List<UserEntity>

    @Query("SELECT * FROM UserEntity WHERE login LIKE :first LIMIT 1")
    fun findByName(first: String, last: String): UserEntity

    @Insert
    fun insertAll(vararg users: UserEntity)

    @Delete
    fun delete(user: UserEntity)
}