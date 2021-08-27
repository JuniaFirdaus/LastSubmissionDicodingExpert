package com.dicoding.foodsmapp.core.data.source.remote

import android.util.Log
import com.dicoding.foodsmapp.core.data.source.remote.network.ApiResponse
import com.dicoding.foodsmapp.core.data.source.remote.network.ApiService
import com.dicoding.foodsmapp.core.data.source.remote.response.CategoriesItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class RemoteDataSource(private val apiService: ApiService) {

    suspend fun getAllTourism(): Flow<ApiResponse<List<CategoriesItem>>> {
        //get data from remote api
        return flow {
            try {
                val response = apiService.getList()
                val dataArray = response.categories
                if (dataArray.isNotEmpty()){
                    emit(ApiResponse.Success(response.categories))
                } else {
                    emit(ApiResponse.Empty)
                }
            } catch (e : Exception){
                emit(ApiResponse.Error(e.toString()))
                Log.e("RemoteDataSource", e.toString())
            }
        }.flowOn(Dispatchers.IO)
    }
}

