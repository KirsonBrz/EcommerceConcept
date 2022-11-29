package com.kirson.ecommerceconcept.repository.dataSourceImpl

import com.kirson.ecommerceconcept.api.MainAPIService
import com.kirson.ecommerceconcept.entity.CartNetworkModel
import com.kirson.ecommerceconcept.entity.PhoneDetailNetworkModel
import com.kirson.ecommerceconcept.entity.PhonesNetworkModel
import com.kirson.ecommerceconcept.repository.dataSource.MainRemoteDataSource
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