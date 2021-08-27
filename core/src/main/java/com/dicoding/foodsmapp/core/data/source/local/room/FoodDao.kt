package com.dicoding.foodsmapp.core.data.source.local.room

import androidx.room.*
import com.dicoding.foodsmapp.core.data.source.local.entity.CategoriesFoodEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface FoodDao {

    @Query("SELECT * FROM categoryfood")
    fun getAllTourism(): Flow<List<CategoriesFoodEntity>>

    @Query("SELECT * FROM categoryfood where isFavorite = 1")
    fun getFavoriteTourism(): Flow<List<CategoriesFoodEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTourism(tourism: List<CategoriesFoodEntity>)

    @Update
    fun updateFavoriteTourism(tourism: CategoriesFoodEntity)
}
