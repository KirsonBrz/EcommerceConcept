package com.kirson.baseproject

import com.kirson.baseproject.entity.APIPhoneDetails
import com.kirson.baseproject.entity.APIPhonesList
import kotlinx.coroutines.flow.Flow


interface MainModel {

    val allPhones: Flow<APIPhonesList>
    val phoneDetails: Flow<APIPhoneDetails>


    suspend fun getAllPhones(): APIPhonesList?
    suspend fun getPhoneDetails(): APIPhoneDetails?

}