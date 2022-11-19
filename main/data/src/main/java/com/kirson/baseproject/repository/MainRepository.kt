package com.kirson.baseproject.repository

import com.kirson.baseproject.entity.APIResponse
import retrofit2.Response

interface MainRepository {

    suspend fun getAllData(): Response<APIResponse>

}