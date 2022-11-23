package com.kirson.baseproject.repository

import com.kirson.baseproject.entity.APIPhoneDetails
import com.kirson.baseproject.entity.APIPhonesList
import retrofit2.Response

interface MainRepository {

    suspend fun getAllPhones(): Response<APIPhonesList>
    suspend fun getPhoneDetails(): Response<APIPhoneDetails>


}