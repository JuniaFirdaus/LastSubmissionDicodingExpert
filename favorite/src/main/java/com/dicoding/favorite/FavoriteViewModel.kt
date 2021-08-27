package com.dicoding.favorite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.dicoding.foodsmapp.core.domain.usecase.FoodUseCase

class FavoriteViewModel(foodUseCase: FoodUseCase) : ViewModel() {
    val favoriteTourism = foodUseCase.getFavoriteTourism().asLiveData()
}

