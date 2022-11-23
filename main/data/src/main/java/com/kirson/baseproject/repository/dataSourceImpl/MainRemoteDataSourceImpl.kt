package com.kirson.baseproject.repository.dataSourceImpl

import com.kirson.baseproject.api.MainAPIService
import com.kirson.baseproject.entity.APIPhoneDetails
import com.kirson.baseproject.entity.APIPhonesList
import com.kirson.baseproject.repository.dataSource.MainRemoteDataSource
import retrofit2.Response

class MainRemoteDataSourceImpl(
    private val mainAPIService: MainAPIService
) : MainRemoteDataSource {
    override suspend fun getPhones(): Response<APIPhonesList> =
        mainAPIService.getPhones()


    override suspend fun getPhoneDetails(): Response<APIPhoneDetails> =
        mainAPIService.getPhoneDetails()


}