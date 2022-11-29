package com.kirson.ecommerceconcept.screens.explorer

import androidx.compose.runtime.Immutable
import com.kirson.ecommerceconcept.core.entity.SortConfiguration
import com.kirson.ecommerceconcept.entity.BestSellerDomainModel
import com.kirson.ecommerceconcept.entity.HomeStoreDomainModel

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