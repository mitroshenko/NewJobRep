package com.mitroshenko.newjob.data.repository.order

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.mitroshenko.newjob.data.repository.order.OrderEntity
import com.mitroshenko.newjob.data.repository.basket.BasketEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface OrderDao {
    @Query("SELECT * FROM order_table")
    fun getAlphabetizedProducts(): Flow<List<OrderEntity>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(orderEntity: OrderEntity)

    @Delete
    suspend fun delete(orderEntity: OrderEntity)

    @Query("DELETE FROM order_table")
    suspend fun deleteAll()

    @Update
    suspend fun update(orderEntity: OrderEntity)
}