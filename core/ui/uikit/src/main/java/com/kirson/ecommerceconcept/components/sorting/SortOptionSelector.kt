package com.kirson.ecommerceconcept.components.sorting

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.accompanist.insets.navigationBarsPadding
import com.kirson.ecommerceconcept.components.HorizontalDivider
import com.kirson.ecommerceconcept.components.SecondaryButtonSmall
import com.kirson.ecommerceconcept.components.buttons.ButtonSlot
import com.kirson.ecommerceconcept.components.buttons.SecondaryButton
import com.kirson.ecommerceconcept.core.entity.SortConfiguration
import com.kirson.ecommerceconcept.core.ui.uikit.R
import com.kirson.ecommerceconcept.ui.theme.EcommerceConceptAppTheme

@Composable
fun SortOptionSelector(
  modifier: Modifier = Modifier,
  props: SortOptionSelectorProps,
  onOptionClick: (optionId: Any) -> Unit,
  onDirectionClick: () -> Unit,
  onApplyClick: () -> Unit,
) {
  Column(
    modifier = modifier
        .background(EcommerceConceptAppTheme.colors.backgroundPrimary)
        .navigationBarsPadding(),
  ) {
    SheetHandle()
    Row(modifier = Modifier.heightIn(min = 48.dp)) {
      Text(
          modifier = Modifier
              .padding(start = 16.dp, end = 16.dp)
              .fillLineHeight(32.sp)
              .weight(1f),
          style = EcommerceConceptAppTheme.typography.title1,
          text = stringResource(id = R.string.sort_option_selector_title),
      )
      SecondaryButtonSmall(
        modifier = Modifier.padding(end = 16.dp),
        onClick = onDirectionClick,
        slotPosition = ButtonSlot.AfterText,
        icon = {
          val icon = when (props.direction) {
            SortConfiguration.SortDirection.Ascending -> R.drawable.ic_arrow_up_round_cap_16
            SortConfiguration.SortDirection.Descending -> R.drawable.ic_arrow_down_round_cap_16
          }
          Icon(
              painter = painterResource(id = icon),
              contentDescription = "sort direction",
              tint = EcommerceConceptAppTheme.colors.textPrimary,
          )
        },
        text = when (props.direction) {
          SortConfiguration.SortDirection.Ascending -> stringResource(id = R.string.sort_option_selector_ascending)
          SortConfiguration.SortDirection.Descending -> stringResource(id = R.string.sort_option_selector_descending)
        }
      )
    }
    props.options.forEachIndexed { index, option ->
      OptionSelectorRow(
        modifier = Modifier.clickable { onOptionClick(option.id) },
        text = option.name,
        checked = option.id == props.selectedOptionId
      )
      if (index != props.options.lastIndex) {
        HorizontalDivider()
      }
    }
    SecondaryButton(
      modifier = Modifier
          .fillMaxWidth()
          .padding(start = 16.dp, end = 16.dp, top = 16.dp, bottom = 24.dp),
      slotPosition = ButtonSlot.End,
      onClick = onApplyClick,
      text = stringResource(id = R.string.action_apply),
      isProgressBarVisible = props.showProgressBar
    )
  }
}

@Immutable
data class SortOptionSelectorProps(
  val direction: SortConfiguration.SortDirection,
  val options: List<Option>,
  val selectedOptionId: Any,
  val showProgressBar: Boolean = false,
) {
  data class Option(
    val id: Any,
    val name: String,
  )
}

@Preview(showBackground = true, widthDp = 360)
@Composable
internal fun SortOptionSelectorPreview() {
    EcommerceConceptAppTheme {
        SortOptionSelector(
            props = SortOptionSelectorProps(
                direction = SortConfiguration.SortDirection.Ascending,
                options = listOf(
                    SortOptionSelectorProps.Option(id = "1", name = "Название"),
                    SortOptionSelectorProps.Option(id = "2", name = "Цена"),
                    SortOptionSelectorProps.Option(id = "3", name = "Изменение цены за день"),
                    SortOptionSelectorProps.Option(id = "4", name = "Изменение цены за квартал"),
                ),
                selectedOptionId = "2",
        showProgressBar = false,
      ),
      onDirectionClick = {},
      onOptionClick = {},
      onApplyClick = {},
    )
  }
}