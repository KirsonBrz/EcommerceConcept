package com.kirson.baseproject

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.viewModelScope
import com.kirson.baseproject.core.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
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
            //mainModel.getAllData(_uiStateFlow.value.baseCurrency)
            observeData()
        }
    }

    fun observeData() {
        viewModelScope.launch(IO) {
//            val rates = mainModel.rates
//            val configuration = mainModel.sortConfiguration
//            val baseCurrency = mainModel.baseCurrency
//            val combineFlow = combine(rates, configuration, baseCurrency, ::Triple)
//
//            combineFlow.flowOn(IO).onStart {
//                withContext(Dispatchers.Main) {
//                    _uiState.value = MainFeatureUIState.Loading(
//                        State().copy(
//                            refreshInProgress = true,
//                            baseCurrency = combineFlow.first().third,
//                            sortConfiguration = combineFlow.first().second
//                        )
//                    )
//                }
//
//            }.flowOn(IO)
//                .onCompletion {
//                    _uiState.value = MainFeatureUIState.Loaded(
//                        State().copy(
//                            refreshInProgress = false,
//                        )
//                    )
//
//                }.flowOn(IO)
//                .catch {
//                    _uiState.value = MainFeatureUIState.Error(
//                        State().copy(
//                            message = "$it"
//                        )
//                    )
//
//                }.flowOn(IO).collect {
//                    _uiState.value = MainFeatureUIState.Loaded(
//                        State().copy(
//                            rates = it.first,
//                            refreshInProgress = false,
//                            baseCurrency = it.third,
//                            sortConfiguration = it.second
//                        )
//                    )
//                }
        }
    }


}