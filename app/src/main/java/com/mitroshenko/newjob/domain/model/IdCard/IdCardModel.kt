package com.mitroshenko.newjob.domain.model.IdCard

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

open class IdCardModel : ViewModel() {
    val idCard : MutableLiveData<Int> = MutableLiveData<Int>()
}