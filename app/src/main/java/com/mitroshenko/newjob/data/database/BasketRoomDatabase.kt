package com.mitroshenko.newjob.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.mitroshenko.newjob.data.database.basket.BasketDao
import com.mitroshenko.newjob.data.database.basket.BasketEntity
import com.mitroshenko.newjob.data.database.favourite.FavouriteDao
import com.mitroshenko.newjob.data.database.favourite.FavouriteEntity
import kotlinx.coroutines.CoroutineScope

@Database(entities = [BasketEntity::class, FavouriteEntity::class], version = 1)
abstract class BasketRoomDatabase : RoomDatabase() {

    abstract fun basketDao(): BasketDao
    abstract fun favouritedao(): FavouriteDao

    companion object {

        private const val DATABASE_NAME = "ProductDatabase"

        @Volatile
        private var INSTANCE: BasketRoomDatabase? = null

        fun getDatabase(
            context: Context,
            scope: CoroutineScope
        ): BasketRoomDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    BasketRoomDatabase::class.java,
                    "product_database"
                )
                    .build()
                INSTANCE = instance
                instance
            }
        }

    }
}