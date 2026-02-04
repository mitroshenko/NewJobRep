package com.mitroshenko.newjob.domain.repository.favourites

import android.content.Context
import androidx.annotation.WorkerThread
import com.mitroshenko.newjob.data.database.BasketRoomDatabase
import com.mitroshenko.newjob.data.database.favourite.FavouriteDao
import com.mitroshenko.newjob.data.database.favourite.FavouriteEntity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.Flow

class FavouriteRepository(private val favouritedao: FavouriteDao) {
    val allFavourite: Flow<List<FavouriteEntity>> = favouritedao.getAlphabetizedFavourites()

    @WorkerThread
    suspend fun insert(favouriteEntity: FavouriteEntity) {
        favouritedao.insert(favouriteEntity)
    }

    suspend fun delete(favouriteEntity: FavouriteEntity) {
        favouritedao.delete(favouriteEntity)
    }

    suspend fun update(favouriteEntity: FavouriteEntity) {
        favouritedao.update(favouriteEntity)
    }

    private var favouriteDatabase: BasketRoomDatabase? = null

    private fun initialiseDB(context: Context): BasketRoomDatabase? {
        val applicationScope = CoroutineScope(SupervisorJob())
        return BasketRoomDatabase.getDatabase(context, applicationScope)
    }
}