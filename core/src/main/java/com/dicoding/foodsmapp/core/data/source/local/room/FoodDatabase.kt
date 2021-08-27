package com.dicoding.foodsmapp.core.data.source.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.dicoding.foodsmapp.core.data.source.local.entity.CategoriesFoodEntity

@Database(entities = [CategoriesFoodEntity::class], version = 1, exportSchema = false)
abstract class FoodDatabase : RoomDatabase() {

    abstract fun foodDao(): FoodDao

}