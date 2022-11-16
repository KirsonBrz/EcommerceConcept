package com.kirson.baseproject.repository.dataSourceImpl

import com.kirson.baseproject.api.MainAPIService
import com.kirson.baseproject.repository.dataSource.MainRemoteDataSource

class MainRemoteDataSourceImpl(
    private val mainAPIService: MainAPIService
) : MainRemoteDataSource {

    override suspend fun getAllData(): String {
        TODO("Not yet implemented")
    }
}