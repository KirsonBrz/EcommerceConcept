package com.kirson.ecommerceconcept

import com.kirson.ecommerceconcept.core.entity.mapDistinctNotNullChanges
import com.kirson.ecommerceconcept.entity.CartDomainModel
import com.kirson.ecommerceconcept.entity.PhoneDetailDomainModel
import com.kirson.ecommerceconcept.entity.PhonesDomainModel
import com.kirson.ecommerceconcept.mappers.toDomainModel
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
        val allPhones: PhonesDomainModel? = null,
        val phoneDetails: PhoneDetailDomainModel? = null,
        val cart: CartDomainModel? = null
    )


    override val allPhones: Flow<PhonesDomainModel>
        get() = stateFlow.mapDistinctNotNullChanges {
            it.allPhones
        }.flowOn(Dispatchers.IO)
    override val phoneDetails: Flow<PhoneDetailDomainModel>
        get() = stateFlow.mapDistinctNotNullChanges {
            it.phoneDetails
        }.flowOn(Dispatchers.IO)
    override val cart: Flow<CartDomainModel>
        get() = stateFlow.mapDistinctNotNullChanges {
            it.cart
        }.flowOn(Dispatchers.IO)

    override suspend fun getAllPhones(): PhonesDomainModel? {

        var phones: PhonesDomainModel? = null

        val data = repository.getAllPhones()

        if (data != null) {
            phones = data.toDomainModel()
        }

        stateFlow.update { state ->
            state.copy(
                allPhones = phones
            )
        }

        return phones
    }


    override suspend fun getPhoneDetails(): PhoneDetailDomainModel? {

        var details: PhoneDetailDomainModel? = null

        val data = repository.getPhoneDetails()

        if (data != null) {
            details = data.toDomainModel()
        }

        stateFlow.update { state ->
            state.copy(
                phoneDetails = details
            )
        }

        return details
    }

    override suspend fun getCart(): CartDomainModel? {
        var cart: CartDomainModel? = null

        val data = repository.getCart()

        if (data != null) {
            cart = data.toDomainModel()
        }

        stateFlow.update { state ->
            state.copy(
                cart = cart
            )
        }

        return cart
    }

}