package com.kirson.ecommerceconcept.core.base

import android.util.Log
import androidx.lifecycle.ViewModel
import com.kirson.ecommerceconcept.navigation.NavTarget

abstract class BaseViewModel<T : ViewModel> : ViewModel() {

  private val className: String? = this::class.simpleName

  init {
    Log.d("ViewModel", "$className initialized")
  }

  open fun navigateToSpecificView(navTarget: NavTarget) {
    Log.d("navigation", "navigate to ${navTarget.route}")
  }

  override fun onCleared() {
    Log.d("ViewModel", "$className cleared")
    super.onCleared()
  }
}