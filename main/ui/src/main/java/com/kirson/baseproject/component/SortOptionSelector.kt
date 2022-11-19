package com.kirson.baseproject.component

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.kirson.baseproject.components.sorting.SortOptionSelectorProps
import com.kirson.baseproject.core.domain.R.string
import com.kirson.baseproject.core.entity.SortConfiguration
import com.kirson.baseproject.components.sorting.SortOptionSelector as SortOptionSelectorCore

@Composable
fun SortOptionSelector(
    modifier: Modifier = Modifier,
    sortConfiguration: SortConfiguration,
    onPropertyClick: (property: SortConfiguration.Property) -> Unit,
    onDirectionClick: () -> Unit,
    onApplyClick: () -> Unit,
) {
    val options = SortConfiguration.Property.values().map { it.toSelectorOption() }
    SortOptionSelectorCore(
        modifier = modifier,
        props = SortOptionSelectorProps(
            direction = sortConfiguration.direction,
            options = options,
            selectedOptionId = sortConfiguration.property.id
        ),
        onOptionClick = { optionId ->
            onPropertyClick(
                SortConfiguration.Property.values().first { it.id == optionId })
        },
        onDirectionClick = onDirectionClick,
        onApplyClick = onApplyClick,
    )
}

@Composable
private fun SortConfiguration.Property.toSelectorOption(): SortOptionSelectorProps.Option {
    val name = when (this) {
        SortConfiguration.Property.Name -> stringResource(id = string.rates_sort_option_name)
        SortConfiguration.Property.Value -> stringResource(id = string.rates_sort_option_price)
    }
    return SortOptionSelectorProps.Option(id = this.id, name = name)
}

private val SortConfiguration.Property.id get() = this