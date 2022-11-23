package com.kirson.baseproject

import com.kirson.baseproject.core.entity.mapDistinctNotNullChanges
import com.kirson.baseproject.entity.APIPhoneDetails
import com.kirson.baseproject.entity.APIPhonesList
import com.kirson.baseproject.repository.MainRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.update

class MainModelImpl(
    private val repository: MainRepository,
) : MainModel {

    private val stateFlow = MutableStateFlow(State())

    data class State(
        val allPhones: APIPhonesList? = null,
        val phoneDetails: APIPhoneDetails? = null
    )


    override val allPhones: Flow<APIPhonesList>
        get() = stateFlow.mapDistinctNotNullChanges {
            it.allPhones
        }.flowOn(Dispatchers.IO)
    override val phoneDetails: Flow<APIPhoneDetails>
        get() = stateFlow.mapDistinctNotNullChanges {
            it.phoneDetails
        }.flowOn(Dispatchers.IO)

    override suspend fun getAllPhones(): APIPhonesList? {
        val response = repository.getAllPhones()
        val data = response.body()
        stateFlow.update { state ->
            state.copy(
                allPhones = data
            )
        }
        return data
    }


    override suspend fun getPhoneDetails(): APIPhoneDetails? {
        val response = repository.getPhoneDetails()
        val data = response.body()
        stateFlow.update { state ->
            state.copy(
                phoneDetails = data
            )
        }
        return data
    }

}