package com.dicoding.foodsmapp.core.data.source.local

import com.dicoding.foodsmapp.core.data.source.local.entity.CategoriesFoodEntity
import com.dicoding.foodsmapp.core.data.source.local.room.FoodDao
import kotlinx.coroutines.flow.Flow

class LocalDataSource(private val foodDao: FoodDao) {

    fun getAllTourism(): Flow<List<CategoriesFoodEntity>> = foodDao.getAllTourism()

    fun getFavoriteTourism(): Flow<List<CategoriesFoodEntity>> = foodDao.getFavoriteTourism()

    suspend fun insertTourism(tourismList: List<CategoriesFoodEntity>) = foodDao.insertTourism(tourismList)

    fun setFavoriteTourism(tourism: CategoriesFoodEntity, newState: Boolean) {
        tourism.isFavorite = newState
        foodDao.updateFavoriteTourism(tourism)
    }
}