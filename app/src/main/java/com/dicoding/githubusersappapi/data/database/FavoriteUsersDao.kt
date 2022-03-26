package com.dicoding.githubusersappapi.data.database

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface FavoriteUsersDao {
    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun insert(favoriteUsers: FavoriteUsers)

    @Query("DELETE FROM favoriteusers WHERE favoriteusers.id = :id" )
    suspend fun delete(id: Int): Int

    @Query("SELECT * FROM favoriteusers ORDER BY login ASC")
    fun getAllFavoriteUsers(): LiveData<List<FavoriteUsers>>

    @Query("SELECT COUNT(*) FROM favoriteusers WHERE favoriteusers.id = :id")
    suspend fun checkFavorite(id: Int): Int
}