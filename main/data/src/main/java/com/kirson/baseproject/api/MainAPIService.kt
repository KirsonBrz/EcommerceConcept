package com.kirson.baseproject.api

import com.kirson.baseproject.entity.APIPhoneDetails
import com.kirson.baseproject.entity.APIPhonesList
import retrofit2.Response
import retrofit2.http.GET


interface MainAPIService {

    @GET("v3/654bd15e-b121-49ba-a588-960956b15175")
    suspend fun getPhones(): Response<APIPhonesList>

    @GET("v3/6c14c560-15c6-4248-b9d2-b4508df7d4f5")
    suspend fun getPhoneDetails(): Response<APIPhoneDetails>

}