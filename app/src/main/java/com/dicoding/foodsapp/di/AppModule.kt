package com.dicoding.foodsapp.di

import com.dicoding.foodsapp.detail.DetailCategoryFoodViewModel
import com.dicoding.foodsapp.home.HomeViewModel
import com.dicoding.foodsmapp.core.domain.usecase.FoodInteractor
import com.dicoding.foodsmapp.core.domain.usecase.FoodUseCase
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val useCaseModule = module {
    factory<FoodUseCase> { FoodInteractor(get()) }
}


val viewModelModule = module {
    viewModel { HomeViewModel(get()) }
    viewModel { DetailCategoryFoodViewModel(get()) }
}