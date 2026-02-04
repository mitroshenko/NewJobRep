package com.mitroshenko.newjob.data.database.favourite

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface FavouriteDao {
    @Query("SELECT * FROM favourite_table")
    fun getAlphabetizedFavourites(): Flow<List<FavouriteEntity>>

    @Insert(onConflict = OnConflictStrategy.Companion.IGNORE)
    suspend fun insert(favouriteEntity: FavouriteEntity)

    @Delete
    suspend fun delete(favouriteEntity: FavouriteEntity)

    @Query("DELETE FROM favourite_table")
    suspend fun deleteAll()

    @Update
    suspend fun update(favouriteEntity: FavouriteEntity)
}