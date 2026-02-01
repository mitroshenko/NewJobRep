package com.mitroshenko.newjob.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.mitroshenko.newjob.data.repository.order.OrderDao
import com.mitroshenko.newjob.data.repository.order.OrderEntity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Database(entities = [OrderEntity::class], version = 1)
abstract class OrderRoomDatabase : RoomDatabase() {

    abstract fun orderDao(): OrderDao

    companion object {

        private const val DATABASE_NAME = "OrderDatabase"

        @Volatile
        private var INSTANCE: OrderRoomDatabase? = null

        fun getDatabase(
            context: Context,
            scope: CoroutineScope
        ): OrderRoomDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    OrderRoomDatabase::class.java,
                    "order_database"
                )
                    .fallbackToDestructiveMigration()
                    .addCallback(OrderDatabaseCallback(scope))
                    .build()
                INSTANCE = instance
                instance
            }
        }
        private class OrderDatabaseCallback(
            private val scope: CoroutineScope
        ) : RoomDatabase.Callback() {
            override fun onCreate(db: SupportSQLiteDatabase) {
                super.onCreate(db)

                INSTANCE?.let { database ->
                    scope.launch(Dispatchers.IO) {
                        populateDatabase(database.orderDao())
                    }
                }
            }
        }
        suspend fun populateDatabase(orderDao: OrderDao) {
            orderDao.deleteAll()
        }
    }
}