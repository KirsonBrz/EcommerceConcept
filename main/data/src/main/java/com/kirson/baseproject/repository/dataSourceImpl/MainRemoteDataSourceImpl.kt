package com.kirson.baseproject.repository.dataSourceImpl

import com.kirson.baseproject.api.MainAPIService
import com.kirson.baseproject.entity.CartNetworkModel
import com.kirson.baseproject.entity.PhoneDetailNetworkModel
import com.kirson.baseproject.entity.PhonesNetworkModel
import com.kirson.baseproject.repository.dataSource.MainRemoteDataSource
import retrofit2.Response

class MainRemoteDataSourceImpl(
    private val mainAPIService: MainAPIService
) : MainRemoteDataSource {
    override suspend fun getPhones(): Response<PhonesNetworkModel> =
        mainAPIService.getPhones()


    override suspend fun getPhoneDetails(): Response<PhoneDetailNetworkModel> =
        mainAPIService.getPhoneDetails()

    override suspend fun getCart(): Response<CartNetworkModel> =
        mainAPIService.getCart()


}