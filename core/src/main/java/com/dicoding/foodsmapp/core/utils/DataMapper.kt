package com.dicoding.foodsmapp.core.utils

import com.dicoding.foodsmapp.core.data.source.remote.response.CategoriesItem
import com.dicoding.foodsmapp.core.domain.model.CategoriesFood

object DataMapper {
    fun mapResponsesToEntities(input: List<CategoriesItem>): List<com.dicoding.foodsmapp.core.data.source.local.entity.CategoriesFoodEntity> {
        val tourismList = ArrayList<com.dicoding.foodsmapp.core.data.source.local.entity.CategoriesFoodEntity>()
        input.map {
            val tourism =
                com.dicoding.foodsmapp.core.data.source.local.entity.CategoriesFoodEntity(
                    categoryId = it.idCategory,
                    strCategory = it.strCategory,
                    strCategoryDescription = it.strCategoryDescription,
                    strCategoryThumb = it.strCategoryThumb,
                    isFavorite = false
                )
            tourismList.add(tourism)
        }
        return tourismList
    }

    fun mapEntitiesToDomain(input: List<com.dicoding.foodsmapp.core.data.source.local.entity.CategoriesFoodEntity>): List<CategoriesFood> =
        input.map {
            CategoriesFood(
                strCategory = it.strCategory,
                strCategoryDescription = it.strCategoryDescription,
                idCategory = it.categoryId,
                strCategoryThumb = it.strCategoryThumb,
                isFavorite = it.isFavorite
            )
        }

    fun mapDomainToEntity(input: CategoriesFood) =
        com.dicoding.foodsmapp.core.data.source.local.entity.CategoriesFoodEntity(
            categoryId = input.idCategory,
            strCategory = input.strCategory,
            strCategoryDescription = input.strCategoryDescription,
            strCategoryThumb = input.strCategoryThumb,
            isFavorite = input.isFavorite
        )
}