package com.kirson.baseproject.api

import com.kirson.baseproject.entity.APIResponse
import retrofit2.Response
import retrofit2.http.GET


interface MainAPIService {

    @GET("v3/654bd15e-b121-49ba-a588-960956b15175")
    suspend fun getData(): Response<APIResponse>

}