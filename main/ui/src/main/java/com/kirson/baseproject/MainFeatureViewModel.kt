package com.kirson.baseproject

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.viewModelScope
import com.kirson.baseproject.core.base.BaseViewModel
import com.kirson.baseproject.core.entity.SortConfiguration
import com.kirson.baseproject.entity.CategoryModel
import com.kirson.baseproject.main.ui.R
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class MainFeatureViewModel @Inject constructor(
    private val mainModel: MainModel,
) : BaseViewModel<MainFeatureViewModel>() {

    private var _uiState = mutableStateOf<MainFeatureUIState>(MainFeatureUIState.Initial)
    val uiState: State<MainFeatureUIState>
        get() = _uiState

    private val _uiStateFlow = MutableStateFlow(State())
    val uiStateFlow = _uiStateFlow.asStateFlow()

    init {
        viewModelScope.launch(IO) {
            mainModel.getAllData()
            observeData()
        }
    }

    fun observeData() {
        viewModelScope.launch(IO) {
            val data = mainModel.allData

            data.flowOn(IO).onStart {
                withContext(Dispatchers.Main) {
                    _uiState.value = MainFeatureUIState.Loading(
                        State().copy(
                            refreshInProgress = true
                        )
                    )
                }
            }.flowOn(IO).onCompletion {
                _uiState.value = MainFeatureUIState.Loaded(
                    State().copy(
                        refreshInProgress = false,
                    )
                )

            }.flowOn(IO).catch {
                _uiState.value = MainFeatureUIState.Error(
                    State().copy(
                        message = "$it"
                    )
                )

            }.flowOn(IO).collect {
                _uiState.value = MainFeatureUIState.Loaded(
                    State().copy(
                        categories = getCategories(),
                        homeStorePhones = it.home_store,
                        bestSellersPhones = it.best_seller,
                        refreshInProgress = false
                    )
                )
            }
        }
    }

    fun dismissSortConfigurationDialog() {
        _uiStateFlow.update {
            it.copy(
                showSortConfigurationDialog = false
            )
        }
    }

    private fun getCategories() = listOf(
        CategoryModel("Phones", R.drawable.phone_24, false),
        CategoryModel("Computer", R.drawable.computer_24, false),
        CategoryModel("Health", R.drawable.health_24, false),
        CategoryModel("Books", R.drawable.books_24, false),
        CategoryModel("Tools", R.drawable.tools_24, false),
        CategoryModel("TV", R.drawable.tv_24, false)
    )

    fun changeSorting() {
        _uiStateFlow.update {
            it.copy(
                showSortConfigurationDialog = true
            )
        }
    }

    fun changeCategory(category: CategoryModel) {
        _uiStateFlow.update {
            it.copy(
                selectedCategory = category
            )
        }

    }

    fun applySortConfiguration(sortConfiguration: SortConfiguration) {
        viewModelScope.launch {
            _uiStateFlow.update { state ->
                state.copy(
                    showSortConfigurationDialog = false,
                    sortConfiguration = sortConfiguration,
                )
            }
            //mainModel.changeSortConfiguration(sortConfiguration)
        }
    }


}