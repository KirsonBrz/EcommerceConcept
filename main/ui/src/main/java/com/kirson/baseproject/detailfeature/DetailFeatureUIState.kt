package com.kirson.baseproject.detailfeature


import androidx.compose.runtime.Immutable
import com.kirson.baseproject.entity.APIPhoneDetails

sealed class DetailFeatureUIState {
    object Initial : DetailFeatureUIState()
    data class Loading(val state: State) : DetailFeatureUIState()
    data class Loaded(val state: State) : DetailFeatureUIState()
    data class Error(val state: State) : DetailFeatureUIState()
}

@Immutable
data class State(
    val refreshInProgress: Boolean = false,
    val message: String? = null,

    val phoneDetails: APIPhoneDetails? = null,


    )