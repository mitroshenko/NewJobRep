package com.mitroshenko.newjob.data.database

import android.app.Application
import com.mitroshenko.newjob.domain.repository.basket.BasketRepository
import com.mitroshenko.newjob.domain.repository.favourites.FavouriteRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob

class Applications : Application() {
    val applicationScope = CoroutineScope(SupervisorJob())
    val basketDatabase by lazy { BasketRoomDatabase.getDatabase(this, applicationScope) }
    val basketRepository by lazy { BasketRepository(basketDatabase.basketDao()) }
    val favouriteRepository by lazy { FavouriteRepository(basketDatabase.favouritedao()) }
}

