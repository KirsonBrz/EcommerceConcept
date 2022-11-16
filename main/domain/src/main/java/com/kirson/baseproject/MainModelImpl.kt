package com.kirson.baseproject

import com.kirson.baseproject.repository.MainRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update

class MainModelImpl (
    private val repository: MainRepository,
) : MainModel {

    private val stateFlow = MutableStateFlow(State())

    data class State(
        val allData: String? = null
    )

    override suspend fun getAllData(): String {
        val data = repository.getAllData()
        stateFlow.update { state ->
            state.copy(
                allData = data
            )
        }
        return data
    }

}