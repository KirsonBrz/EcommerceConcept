package com.kirson.ecommerceconcept.components

import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetState
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect

// See NOTE_BOTTOM_SHEET_HACK
@OptIn(ExperimentalMaterialApi::class)
@Composable
fun BottomSheetExpandHideEffect(
  showDialog: Boolean,
  sheetState: ModalBottomSheetState,
) {
  LaunchedEffect(showDialog, sheetState.targetValue) {
    if (showDialog) {
      sheetState.animateTo(ModalBottomSheetValue.Expanded)
    } else {
      sheetState.hide()
    }
  }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun BottomSheetExpandHideEffect(
  showDialog: Boolean,
  sheetState: ModalBottomSheetState,
  onHide: () -> Unit,
) {
  LaunchedEffect(showDialog, sheetState.targetValue) {
    if (showDialog) {
      sheetState.animateTo(ModalBottomSheetValue.Expanded)
    } else {
      sheetState.hide()
      onHide()
    }
  }
}
