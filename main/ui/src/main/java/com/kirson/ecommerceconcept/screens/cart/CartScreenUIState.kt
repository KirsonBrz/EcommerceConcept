package com.kirson.ecommerceconcept.screens.cart

import androidx.compose.runtime.Immutable
import com.kirson.ecommerceconcept.entity.CartDomainModel


sealed class CartScreenUIState {
    object Initial : CartScreenUIState()
    data class Loading(val state: State) : CartScreenUIState()
    data class Loaded(val state: State) : CartScreenUIState()
    data class Error(val state: State) : CartScreenUIState()
}

@Immutable
data class State(
    val refreshInProgress: Boolean = false,
    val message: String? = null,

    val cartDomainModel: CartDomainModel? = null,


    )

