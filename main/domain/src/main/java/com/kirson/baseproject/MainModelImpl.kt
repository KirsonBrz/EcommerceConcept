package com.kirson.baseproject

import com.kirson.baseproject.core.entity.mapDistinctNotNullChanges
import com.kirson.baseproject.entity.APIResponse
import com.kirson.baseproject.repository.MainRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.update

class MainModelImpl (
    private val repository: MainRepository,
) : MainModel {

    private val stateFlow = MutableStateFlow(State())

    data class State(
        val allData: APIResponse? = null
    )

    override val allData: Flow<APIResponse>
        get() = stateFlow.mapDistinctNotNullChanges {
            it.allData
        }.flowOn(Dispatchers.IO)

    override suspend fun getAllData(): APIResponse? {
        val response = repository.getAllData()
        val data = response.body()
        stateFlow.update { state ->
            state.copy(
                allData = data
            )
        }
        return data
    }

}