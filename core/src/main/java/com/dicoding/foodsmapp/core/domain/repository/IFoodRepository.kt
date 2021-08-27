package com.dicoding.foodsmapp.core.domain.repository

import com.dicoding.foodsmapp.core.domain.model.CategoriesFood
import kotlinx.coroutines.flow.Flow

interface IFoodRepository {

    fun getAllTourism(): Flow<com.dicoding.foodsmapp.core.data.Resource<List<CategoriesFood>>>

    fun getFavoriteTourism(): Flow<List<CategoriesFood>>

    fun setFavoriteTourism(tourism: CategoriesFood, state: Boolean)

}