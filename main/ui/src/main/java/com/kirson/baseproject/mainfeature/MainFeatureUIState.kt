package com.kirson.baseproject.mainfeature

import androidx.compose.runtime.Immutable
import com.kirson.baseproject.core.entity.SortConfiguration
import com.kirson.baseproject.entity.BestSeller
import com.kirson.baseproject.entity.HomeStore

sealed class MainFeatureUIState {
    object Initial : MainFeatureUIState()
    data class Loading(val state: State) : MainFeatureUIState()
    data class Loaded(val state: State) : MainFeatureUIState()
    data class Error(val state: State) : MainFeatureUIState()
}

@Immutable
data class State(
    val refreshInProgress: Boolean = false,
    val message: String? = null,

    val homeStorePhones: List<HomeStore>? = null,
    val bestSellersPhones: List<BestSeller>? = null,


    val sortConfiguration: SortConfiguration = SortConfiguration(
        SortConfiguration.Property.Name,
        SortConfiguration.SortDirection.Ascending
    ),
    val showSortConfigurationDialog: Boolean = false,
)