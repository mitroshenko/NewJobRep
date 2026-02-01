package com.mitroshenko.newjob.presentation.ui.order

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.CreationExtras
import com.mitroshenko.newjob.data.database.Applications
import com.mitroshenko.newjob.data.repository.basket.BasketEntity
import com.mitroshenko.newjob.data.repository.basket.BasketRepository
import com.mitroshenko.newjob.data.repository.favourites.FavouriteEntity
import com.mitroshenko.newjob.data.repository.order.OrderEntity
import com.mitroshenko.newjob.data.repository.order.OrderRepository
import kotlinx.coroutines.launch

class OrderViewModel(private val basketRepository: BasketRepository) : ViewModel() {
    val allProducts: LiveData<List<BasketEntity>> = basketRepository.allProducts.asLiveData()
    }