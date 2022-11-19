package com.kirson.baseproject.repository.dataSourceImpl

import com.kirson.baseproject.api.MainAPIService
import com.kirson.baseproject.entity.APIResponse
import com.kirson.baseproject.repository.dataSource.MainRemoteDataSource
import retrofit2.Response

class MainRemoteDataSourceImpl(
    private val mainAPIService: MainAPIService
) : MainRemoteDataSource {

    override suspend fun getData(): Response<APIResponse> =
        mainAPIService.getData()
}