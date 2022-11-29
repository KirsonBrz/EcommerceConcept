package com.kirson.ecommerceconcept.repository.dataSource

import com.kirson.ecommerceconcept.entity.CartNetworkModel
import com.kirson.ecommerceconcept.entity.PhoneDetailNetworkModel
import com.kirson.ecommerceconcept.entity.PhonesNetworkModel
import retrofit2.Response

interface MainRemoteDataSource {

    suspend fun getPhones(): Response<PhonesNetworkModel>
    suspend fun getPhoneDetails(): Response<PhoneDetailNetworkModel>

    suspend fun getCart(): Response<CartNetworkModel>


}