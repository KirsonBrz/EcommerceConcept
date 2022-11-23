package com.kirson.baseproject.detailfeature

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.viewModelScope
import com.kirson.baseproject.MainModel
import com.kirson.baseproject.core.base.BaseViewModel
import com.kirson.baseproject.entity.APIPhoneDetails
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class DetailFeatureViewModel @Inject constructor(
    private val mainModel: MainModel
) : BaseViewModel<DetailFeatureViewModel>() {

    private var _uiState = mutableStateOf<DetailFeatureUIState>(DetailFeatureUIState.Initial)
    val uiState: State<DetailFeatureUIState>
        get() = _uiState

    private val _uiStateFlow = MutableStateFlow(State())
    val uiStateFlow = _uiStateFlow.asStateFlow()

    init {
        viewModelScope.launch(Dispatchers.IO) {
            mainModel.getPhoneDetails()
            observeData()
        }
    }

    fun observeData() {
        viewModelScope.launch(Dispatchers.IO) {
            val details = mainModel.phoneDetails

            details.flowOn(Dispatchers.IO).onStart {
                withContext(Dispatchers.Main) {
                    _uiState.value = DetailFeatureUIState.Loading(
                        State().copy(
                            refreshInProgress = true
                        )
                    )
                }
            }.flowOn(Dispatchers.IO).onCompletion {
                _uiState.value = DetailFeatureUIState.Loaded(
                    State().copy(
                        refreshInProgress = false,
                    )
                )

            }.flowOn(Dispatchers.IO).catch {
                _uiState.value = DetailFeatureUIState.Error(
                    State().copy(
                        message = "$it"
                    )
                )

            }.flowOn(Dispatchers.IO).collect { phoneDetails ->
                _uiState.value = DetailFeatureUIState.Loaded(
                    State().copy(
                        phoneDetails = phoneDetails,
                        refreshInProgress = false
                    )
                )

            }

        }
    }

    fun addToCart(phoneDetails: APIPhoneDetails) {

    }


}