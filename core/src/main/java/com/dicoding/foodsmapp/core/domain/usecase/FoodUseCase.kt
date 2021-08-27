package com.dicoding.foodsmapp.core.domain.usecase

import com.dicoding.foodsmapp.core.domain.model.CategoriesFood
import kotlinx.coroutines.flow.Flow

interface FoodUseCase {
    fun getAllTourism(): Flow<com.dicoding.foodsmapp.core.data.Resource<List<CategoriesFood>>>
    fun getFavoriteTourism(): Flow<List<CategoriesFood>>
    fun setFavoriteTourism(tourism: CategoriesFood, state: Boolean)
}