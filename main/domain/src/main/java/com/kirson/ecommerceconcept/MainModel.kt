package com.kirson.ecommerceconcept

import com.kirson.ecommerceconcept.entity.CartDomainModel
import com.kirson.ecommerceconcept.entity.PhoneDetailDomainModel
import com.kirson.ecommerceconcept.entity.PhonesDomainModel
import kotlinx.coroutines.flow.Flow


interface MainModel {

    val allPhones: Flow<PhonesDomainModel>
    val phoneDetails: Flow<PhoneDetailDomainModel>
    val cart: Flow<CartDomainModel>


    suspend fun getAllPhones(): PhonesDomainModel?
    suspend fun getPhoneDetails(): PhoneDetailDomainModel?
    suspend fun getCart(): CartDomainModel?

}