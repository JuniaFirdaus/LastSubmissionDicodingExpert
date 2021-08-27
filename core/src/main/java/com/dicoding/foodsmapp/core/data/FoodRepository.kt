package com.dicoding.foodsmapp.core.data

import com.dicoding.foodsmapp.core.data.source.remote.network.ApiResponse
import com.dicoding.foodsmapp.core.data.source.remote.response.CategoriesItem
import com.dicoding.foodsmapp.core.domain.model.CategoriesFood
import com.dicoding.foodsmapp.core.domain.repository.IFoodRepository
import com.dicoding.foodsmapp.core.utils.AppExecutors
import com.dicoding.foodsmapp.core.utils.DataMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class FoodRepository(
    private val remoteDataSource: com.dicoding.foodsmapp.core.data.source.remote.RemoteDataSource,
    private val localDataSource: com.dicoding.foodsmapp.core.data.source.local.LocalDataSource,
    private val appExecutors: AppExecutors
) : IFoodRepository {


    override fun getAllTourism(): Flow<Resource<List<CategoriesFood>>> =
        object :
            com.dicoding.foodsmapp.core.data.NetworkBoundResource<List<CategoriesFood>, List<CategoriesItem>>() {
            override fun loadFromDB(): Flow<List<CategoriesFood>> {
                return localDataSource.getAllTourism().map {
                    DataMapper.mapEntitiesToDomain(it)
                }
            }

            override fun shouldFetch(data: List<CategoriesFood>?): Boolean =
                true

            override suspend fun createCall(): Flow<ApiResponse<List<CategoriesItem>>> =
                remoteDataSource.getAllTourism()

            override suspend fun saveCallResult(data: List<CategoriesItem>) {
                val tourismList = DataMapper.mapResponsesToEntities(data)
                localDataSource.insertTourism(tourismList)
            }
        }.asFlow()

    override fun getFavoriteTourism(): Flow<List<CategoriesFood>> {
        return localDataSource.getFavoriteTourism().map {
            DataMapper.mapEntitiesToDomain(it)
        }
    }

    override fun setFavoriteTourism(tourism: CategoriesFood, state: Boolean) {
        val tourismEntity = DataMapper.mapDomainToEntity(tourism)
        appExecutors.diskIO().execute { localDataSource.setFavoriteTourism(tourismEntity, state) }
    }
}

