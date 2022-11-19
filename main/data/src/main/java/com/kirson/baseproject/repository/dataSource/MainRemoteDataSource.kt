package com.kirson.baseproject.repository.dataSource

import com.kirson.baseproject.entity.APIResponse
import retrofit2.Response

interface MainRemoteDataSource {

    suspend fun getData(): Response<APIResponse>
}