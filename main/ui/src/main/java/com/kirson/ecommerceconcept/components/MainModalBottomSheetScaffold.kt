package com.kirson.ecommerceconcept.components

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetLayout
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.kirson.ecommerceconcept.core.entity.SortConfiguration
import com.kirson.ecommerceconcept.screens.explorer.State
import com.kirson.ecommerceconcept.ui.theme.EcommerceConceptAppTheme
import com.kirson.ecommerceconcept.ui.theme.Shapes

@OptIn(ExperimentalMaterialApi::class)
@Composable
internal fun MainModalBottomSheetScaffold(
    state: State,
    content: @Composable () -> Unit,
    applySortConfiguration: (SortConfiguration) -> Unit,
    dismissSortConfigurationDialog: () -> Unit
) {
    val sheetState = rememberModalBottomSheetState(
        initialValue = ModalBottomSheetValue.Hidden,
        confirmStateChange = { sheetValue ->
            if (sheetValue == ModalBottomSheetValue.Hidden) {
                dismissSortConfigurationDialog()
            }
            false
        })

    var showDialog by remember { mutableStateOf(value = state.showSortConfigurationDialog) }
    if (state.showSortConfigurationDialog) {
        showDialog = true
    }

    BottomSheetExpandHideEffect(state.showSortConfigurationDialog, sheetState) {
        showDialog = false
    }

    ModalBottomSheetLayout(
        sheetState = sheetState,
        sheetShape = Shapes.large.copy(
            bottomStart = CornerSize(percent = 0), bottomEnd = CornerSize(percent = 0)
        ),
        scrimColor = EcommerceConceptAppTheme.colors.shadowColor.copy(alpha = 0.4f),
        sheetBackgroundColor = Color.Transparent,
        sheetContent = {
            ModalSheetContent(
                showSortConfigurationDialog = showDialog,
                sortConfiguration = state.sortConfiguration,
                applySortConfiguration = applySortConfiguration
            )
        },
        content = {
            content()
        })
}

@Composable
private fun ModalSheetContent(
    sortConfiguration: SortConfiguration? = null,
    showSortConfigurationDialog: Boolean = false,
    applySortConfiguration: (SortConfiguration) -> Unit
) {
    if (showSortConfigurationDialog && sortConfiguration != null) {
        var sortConfiguration by remember(showSortConfigurationDialog) {
            mutableStateOf(sortConfiguration)
        }
        SortOptionSelector(sortConfiguration = sortConfiguration, onDirectionClick = {
            sortConfiguration = sortConfiguration.let { configuration ->
                val newDirection = when (configuration.direction) {
                    SortConfiguration.SortDirection.Ascending -> SortConfiguration.SortDirection.Descending
                    SortConfiguration.SortDirection.Descending -> SortConfiguration.SortDirection.Ascending
                }
                configuration.copy(direction = newDirection)
            }
        }, onPropertyClick = { property ->
            sortConfiguration = sortConfiguration.copy(property = property)
        }, onApplyClick = {
            applySortConfiguration(sortConfiguration)
        })
    } else {
        Spacer(modifier = Modifier.height(24.dp))
    }
}
