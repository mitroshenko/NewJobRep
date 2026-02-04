package com.mitroshenko.newjob.data.database.basket

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface BasketDao {
    @Query("SELECT * FROM my_table")
    fun getAlphabetizedProducts(): Flow<List<BasketEntity>>

    @Insert(onConflict = OnConflictStrategy.Companion.IGNORE)
    suspend fun insert(basketEntity: BasketEntity)

    @Delete
    suspend fun delete(basketEntity: BasketEntity)

    @Query("DELETE FROM my_table")
    suspend fun deleteAll()

    @Update
    suspend fun update(basketEntity: BasketEntity)
}

