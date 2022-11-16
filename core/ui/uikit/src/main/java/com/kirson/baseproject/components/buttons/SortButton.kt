package com.kirson.baseproject.components.buttons

import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.kirson.baseproject.components.SecondaryButtonSmall
import com.kirson.baseproject.core.entity.SortConfiguration
import com.kirson.baseproject.core.ui.uikit.R
import com.kirson.baseproject.ui.theme.BaseProjectAppTheme

@Composable
fun SortButton(
  modifier: Modifier = Modifier,
  onClick: () -> Unit,
  text: String,
  direction: SortConfiguration.SortDirection,
) {
  SecondaryButtonSmall(
    modifier = modifier,
    onClick = onClick,
    slotPosition = ButtonSlot.AfterText,
    icon = {
      val icon = when (direction) {
        SortConfiguration.SortDirection.Ascending -> R.drawable.ic_arrow_up_round_cap_16
        SortConfiguration.SortDirection.Descending -> R.drawable.ic_arrow_down_round_cap_16
      }
      Icon(
        painter = painterResource(id = icon),
        contentDescription = "sort direction",
        tint = BaseProjectAppTheme.colors.black
      )
    },
    text = text
  )
}

@Preview(showBackground = true, widthDp = 360)
@Composable
internal fun SortButtonPreview() {
  BaseProjectAppTheme {
    SortButton(
      direction = SortConfiguration.SortDirection.Ascending,
      text = "Название",
      onClick = {}
    )
  }
}
