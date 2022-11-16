package com.kirson.baseproject.repository


import com.kirson.baseproject.repository.dataSource.MainRemoteDataSource

class MainRepositoryImpl(
    private val mainRemoteDataSource: MainRemoteDataSource,
    //private val mainLocalDataSource: MainLocalDataSource
    //private val database: FavoriteCurrenciesDatabase,
) : MainRepository {


    override suspend fun getAllData(): String {
        TODO("Not yet implemented")
    }

}