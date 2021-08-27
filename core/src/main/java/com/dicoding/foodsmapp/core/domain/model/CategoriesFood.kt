package com.dicoding.foodsmapp.core.domain.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class CategoriesFood(

    val strCategory: String,
    val strCategoryDescription: String,
    val idCategory: String,
    val strCategoryThumb: String,
    val isFavorite: Boolean
) : Parcelable