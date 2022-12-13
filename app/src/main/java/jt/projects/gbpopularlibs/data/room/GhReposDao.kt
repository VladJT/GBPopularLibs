package jt.projects.gbpopularlibs.data.room

import androidx.room.*
import jt.projects.gbpopularlibs.domain.entities.GhRepoEntity

@Dao
interface GhReposDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(repo: GhRepoEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(vararg repos: GhRepoEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(repos: List<GhRepoEntity>)

    @Update
    fun update(repo: GhRepoEntity)

    @Update
    fun update(vararg repos: GhRepoEntity)

    @Update
    fun update(repos: List<GhRepoEntity>)

    @Delete
    fun delete(repo: GhRepoEntity)

    @Delete
    fun delete(vararg repos: GhRepoEntity)

    @Delete
    fun delete(repos: List<GhRepoEntity>)

    @Query("SELECT * FROM GhRepoEntity")
    fun getAll(): List<GhRepoEntity>

    @Query("SELECT * FROM GhRepoEntity WHERE userId = :userId")
    fun findReposByUserId(userId: Int): List<GhRepoEntity>
}