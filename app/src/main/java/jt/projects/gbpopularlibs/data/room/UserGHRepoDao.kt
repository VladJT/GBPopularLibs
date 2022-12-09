package jt.projects.gbpopularlibs.data.room

import androidx.room.*
import jt.projects.gbpopularlibs.domain.entities.UserGHRepo

@Dao
interface UserGHRepoDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(repo: UserGHRepo)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(vararg repos: UserGHRepo)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(repos: List<UserGHRepo>)

    @Update
    fun update(repo: UserGHRepo)

    @Update
    fun update(vararg repos: UserGHRepo)

    @Update
    fun update(repos: List<UserGHRepo>)

    @Delete
    fun delete(repo: UserGHRepo)

    @Delete
    fun delete(vararg repos: UserGHRepo)

    @Delete
    fun delete(repos: List<UserGHRepo>)

    @Query("SELECT * FROM UserGHRepo")
    fun getAll(): List<UserGHRepo>

    @Query("SELECT * FROM UserGHRepo WHERE userId = :userId")
    fun findReposByUser(userId: String): List<UserGHRepo>

}