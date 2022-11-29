package com.kirson.ecommerceconcept


import com.kirson.ecommerceconcept.entity.CartNetworkModel
import com.kirson.ecommerceconcept.entity.PhoneDetailNetworkModel
import com.kirson.ecommerceconcept.entity.PhonesNetworkModel

interface MainRepository {

    suspend fun getAllPhones(): PhonesNetworkModel?
    suspend fun getPhoneDetails(): PhoneDetailNetworkModel?
    suspend fun getCart(): CartNetworkModel?


}