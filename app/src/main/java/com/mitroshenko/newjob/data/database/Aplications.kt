package com.mitroshenko.newjob.data.database

import android.app.Application
import com.mitroshenko.newjob.data.repository.basket.BasketRepository
import com.mitroshenko.newjob.data.repository.favourites.FavouriteRepository
import com.mitroshenko.newjob.data.repository.order.OrderRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob

class Applications : Application() {
    val applicationScope = CoroutineScope(SupervisorJob())
    val basketDatabase by lazy { BasketRoomDatabase.getDatabase(this, applicationScope) }
    val favouriteDatabase by lazy { FavouriteRoomDatabase.getDatabase(this, applicationScope)}
    val orderDatabase by lazy { OrderRoomDatabase.getDatabase(this, applicationScope)}
    val basketRepository by lazy { BasketRepository(basketDatabase.dao()) }
    val favouriteRepository by lazy { FavouriteRepository(favouriteDatabase.favouritedao()) }
    val orderRepository by lazy { OrderRepository(orderDatabase.orderDao()) }
}

