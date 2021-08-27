package com.dicoding.foodsmapp.core.domain.usecase

import com.dicoding.foodsmapp.core.domain.model.CategoriesFood
import com.dicoding.foodsmapp.core.domain.repository.IFoodRepository


class FoodInteractor(private val foodRepository: IFoodRepository): FoodUseCase {

    override fun getAllTourism() = foodRepository.getAllTourism()

    override fun getFavoriteTourism() = foodRepository.getFavoriteTourism()

    override fun setFavoriteTourism(tourism: CategoriesFood, state: Boolean) = foodRepository.setFavoriteTourism(tourism, state)
}