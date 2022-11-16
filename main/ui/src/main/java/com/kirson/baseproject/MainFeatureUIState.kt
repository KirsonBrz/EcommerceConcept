package com.kirson.baseproject

import androidx.compose.runtime.Immutable

sealed class MainFeatureUIState {
    object Initial : MainFeatureUIState()
    data class Loading(val state: State) : MainFeatureUIState()
    data class Loaded(val state: State) : MainFeatureUIState()
    data class Error(val state: State) : MainFeatureUIState()
}

@Immutable
data class State(
    val refreshInProgress: Boolean = false,
    val message: String? = null
)