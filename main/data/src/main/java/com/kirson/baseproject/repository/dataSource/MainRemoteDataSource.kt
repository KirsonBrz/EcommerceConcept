package com.kirson.baseproject.repository.dataSource

import com.kirson.baseproject.entity.CartNetworkModel
import com.kirson.baseproject.entity.PhoneDetailNetworkModel
import com.kirson.baseproject.entity.PhonesNetworkModel
import retrofit2.Response

interface MainRemoteDataSource {

    suspend fun getPhones(): Response<PhonesNetworkModel>
    suspend fun getPhoneDetails(): Response<PhoneDetailNetworkModel>

    suspend fun getCart(): Response<CartNetworkModel>


}