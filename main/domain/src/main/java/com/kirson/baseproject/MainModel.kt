package com.kirson.baseproject

import com.kirson.baseproject.entity.APIResponse
import kotlinx.coroutines.flow.Flow


interface MainModel {

    val allData: Flow<APIResponse>


    suspend fun getAllData(): APIResponse?

}