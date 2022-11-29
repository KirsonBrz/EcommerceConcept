package com.kirson.baseproject


import com.kirson.baseproject.entity.CartNetworkModel
import com.kirson.baseproject.entity.PhoneDetailNetworkModel
import com.kirson.baseproject.entity.PhonesNetworkModel

interface MainRepository {

    suspend fun getAllPhones(): PhonesNetworkModel?
    suspend fun getPhoneDetails(): PhoneDetailNetworkModel?
    suspend fun getCart(): CartNetworkModel?


}