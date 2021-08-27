package com.dicoding.foodsmapp.core.data.source.local.entity

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "categoryfood")
data class CategoriesFoodEntity(

    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "categoryId")
    var categoryId: String,

    @ColumnInfo(name = "strCategory")
    var strCategory: String,

    @ColumnInfo(name = "strCategoryDescription")
    var strCategoryDescription: String,

    @ColumnInfo(name = "strCategoryThumb")
    var strCategoryThumb: String,

    @ColumnInfo(name = "isFavorite")
    var isFavorite: Boolean = false,

    )