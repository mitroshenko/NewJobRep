package com.mitroshenko.newjob.presentation.ui.order

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.mitroshenko.newjob.data.database.basket.BasketEntity
import com.mitroshenko.newjob.domain.repository.basket.BasketRepository

class OrderViewModel(private val basketRepository: BasketRepository) : ViewModel() {
    val allProducts: LiveData<List<BasketEntity>> = basketRepository.allProducts.asLiveData()
    }