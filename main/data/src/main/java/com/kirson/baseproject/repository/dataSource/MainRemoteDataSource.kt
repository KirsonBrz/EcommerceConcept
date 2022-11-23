package com.kirson.baseproject.repository.dataSource

import com.kirson.baseproject.entity.APIPhoneDetails
import com.kirson.baseproject.entity.APIPhonesList
import retrofit2.Response

interface MainRemoteDataSource {

    suspend fun getPhones(): Response<APIPhonesList>
    suspend fun getPhoneDetails(): Response<APIPhoneDetails>
}