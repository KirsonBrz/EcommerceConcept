package com.kirson.baseproject.screens.explorer

import androidx.compose.runtime.Immutable
import com.kirson.baseproject.core.entity.SortConfiguration
import com.kirson.baseproject.entity.BestSellerDomainModel
import com.kirson.baseproject.entity.HomeStoreDomainModel

sealed class ExplorerScreenUIState {
    object Initial : ExplorerScreenUIState()
    data class Loading(val state: State) : ExplorerScreenUIState()
    data class Loaded(val state: State) : ExplorerScreenUIState()
    data class Error(val state: State) : ExplorerScreenUIState()
}

@Immutable
data class State(
    val refreshInProgress: Boolean = false,
    val message: String? = null,


    val homeStorePhones: List<HomeStoreDomainModel>? = null,
    val bestSellersPhones: List<BestSellerDomainModel>? = null,


    val sortConfiguration: SortConfiguration = SortConfiguration(
        SortConfiguration.Property.Name,
        SortConfiguration.SortDirection.Ascending
    ),
    val showSortConfigurationDialog: Boolean = false,
)