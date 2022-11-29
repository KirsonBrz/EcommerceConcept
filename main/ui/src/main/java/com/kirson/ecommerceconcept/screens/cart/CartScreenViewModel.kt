package com.kirson.ecommerceconcept.screens.cart

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.viewModelScope
import com.kirson.ecommerceconcept.MainModel
import com.kirson.ecommerceconcept.core.base.BaseViewModel
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
class CartScreenViewModel @Inject constructor(
    private val mainModel: MainModel
) : BaseViewModel<CartScreenViewModel>() {

    private var _uiState = mutableStateOf<CartScreenUIState>(CartScreenUIState.Initial)
    val uiState: State<CartScreenUIState>
        get() = _uiState

    private val _uiStateFlow = MutableStateFlow(State())
    val uiStateFlow = _uiStateFlow.asStateFlow()

    init {
        viewModelScope.launch(Dispatchers.IO) {
            mainModel.getCart()
            observeCart()
        }
    }

    fun observeCart() {
        viewModelScope.launch(Dispatchers.IO) {

            val cart = mainModel.cart



            cart.flowOn(Dispatchers.IO).onStart {
                withContext(Dispatchers.Main) {
                    _uiState.value = CartScreenUIState.Loading(
                        State().copy(
                            refreshInProgress = true
                        )
                    )
                }
            }.flowOn(Dispatchers.IO).onCompletion {
                _uiState.value = CartScreenUIState.Loaded(
                    State().copy(
                        refreshInProgress = false,
                    )
                )

            }.flowOn(Dispatchers.IO).catch {
                _uiState.value = CartScreenUIState.Error(
                    State().copy(
                        message = "$it"
                    )
                )

            }.flowOn(Dispatchers.IO).collect { cartData ->
                _uiState.value = CartScreenUIState.Loaded(
                    State().copy(
                        cartDomainModel = cartData,
                        refreshInProgress = false
                    )
                )

            }

        }
    }


}