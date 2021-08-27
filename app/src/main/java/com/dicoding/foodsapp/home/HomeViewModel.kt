package com.dicoding.foodsapp.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.dicoding.foodsmapp.core.domain.usecase.FoodUseCase

class HomeViewModel(foodUseCase: FoodUseCase) : ViewModel() {
    val tourism = foodUseCase.getAllTourism().asLiveData()
}

