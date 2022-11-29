package com.kirson.ecommerceconcept.screens.details


import androidx.compose.runtime.Immutable
import com.kirson.ecommerceconcept.entity.PhoneDetailDomainModel

sealed class DetailsScreenUIState {
    object Initial : DetailsScreenUIState()
    data class Loading(val state: State) : DetailsScreenUIState()
    data class Loaded(val state: State) : DetailsScreenUIState()
    data class Error(val state: State) : DetailsScreenUIState()
}

@Immutable
data class State(
    val refreshInProgress: Boolean = false,
    val message: String? = null,

    val phoneDetails: PhoneDetailDomainModel? = null,


    )