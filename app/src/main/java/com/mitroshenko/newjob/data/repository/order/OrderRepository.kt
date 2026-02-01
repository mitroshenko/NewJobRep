package com.mitroshenko.newjob.data.repository.order

import android.content.Context
import androidx.annotation.WorkerThread
import com.mitroshenko.newjob.data.database.BasketRoomDatabase
import com.mitroshenko.newjob.data.database.OrderRoomDatabase
import com.mitroshenko.newjob.data.repository.basket.BasketDao
import com.mitroshenko.newjob.data.repository.basket.BasketEntity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.Flow

class OrderRepository(private val orderDao: OrderDao) {
    val allProducts: Flow<List<OrderEntity>> = orderDao.getAlphabetizedProducts()

    @WorkerThread
    suspend fun insert(orderEntity: OrderEntity) {
        orderDao.insert(orderEntity)
    }

    suspend fun delete(orderEntity: OrderEntity) {
        orderDao.delete(orderEntity)
    }

    suspend fun update(orderEntity: OrderEntity) {
        orderDao.update(orderEntity)
    }

    private var prodDatabase: OrderRoomDatabase? = null

    private fun initialiseDB(context: Context): OrderRoomDatabase {
        val applicationScope = CoroutineScope(SupervisorJob())
        return OrderRoomDatabase.getDatabase(context, applicationScope)
    }
}