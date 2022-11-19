package com.kirson.baseproject.repository


import com.kirson.baseproject.entity.APIResponse
import com.kirson.baseproject.repository.dataSource.MainRemoteDataSource
import retrofit2.Response

class MainRepositoryImpl(
    private val mainRemoteDataSource: MainRemoteDataSource,
    //private val mainLocalDataSource: MainLocalDataSource
    //private val database: FavoriteCurrenciesDatabase,
) : MainRepository {


    override suspend fun getAllData(): Response<APIResponse> =
        mainRemoteDataSource.getData()


}