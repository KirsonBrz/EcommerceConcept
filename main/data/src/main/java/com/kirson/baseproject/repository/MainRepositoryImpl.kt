package com.kirson.baseproject.repository


import com.kirson.baseproject.entity.APIPhoneDetails
import com.kirson.baseproject.entity.APIPhonesList
import com.kirson.baseproject.repository.dataSource.MainRemoteDataSource
import retrofit2.Response

class MainRepositoryImpl(
    private val mainRemoteDataSource: MainRemoteDataSource,
    //private val mainLocalDataSource: MainLocalDataSource
    //private val database: FavoriteCurrenciesDatabase,
) : MainRepository {


    override suspend fun getAllPhones(): Response<APIPhonesList> =
        mainRemoteDataSource.getPhones()

    override suspend fun getPhoneDetails(): Response<APIPhoneDetails> =
        mainRemoteDataSource.getPhoneDetails()


}