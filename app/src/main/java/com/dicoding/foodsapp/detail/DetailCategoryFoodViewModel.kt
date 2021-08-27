package com.dicoding.foodsapp.detail

import androidx.lifecycle.ViewModel
import com.dicoding.foodsmapp.core.domain.model.CategoriesFood
import com.dicoding.foodsmapp.core.domain.usecase.FoodUseCase

class DetailCategoryFoodViewModel(private val foodUseCase: FoodUseCase) : ViewModel() {
    fun setFavoriteTourism(tourism: CategoriesFood, newStatus:Boolean) =
        foodUseCase.setFavoriteTourism(tourism, newStatus)
}

