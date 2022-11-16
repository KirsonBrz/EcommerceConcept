package com.kirson.baseproject.repository.dataSource

interface MainRemoteDataSource {

    suspend fun getAllData() : String
}