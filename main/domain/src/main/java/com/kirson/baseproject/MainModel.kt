package com.kirson.baseproject

import com.kirson.baseproject.entity.CartDomainModel
import com.kirson.baseproject.entity.PhoneDetailDomainModel
import com.kirson.baseproject.entity.PhonesDomainModel
import kotlinx.coroutines.flow.Flow


interface MainModel {

    val allPhones: Flow<PhonesDomainModel>
    val phoneDetails: Flow<PhoneDetailDomainModel>
    val cart: Flow<CartDomainModel>


    suspend fun getAllPhones(): PhonesDomainModel?
    suspend fun getPhoneDetails(): PhoneDetailDomainModel?
    suspend fun getCart(): CartDomainModel?

}