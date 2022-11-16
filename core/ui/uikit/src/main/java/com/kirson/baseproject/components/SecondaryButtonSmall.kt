package com.kirson.baseproject.components

import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.kirson.baseproject.components.buttons.Button
import com.kirson.baseproject.components.buttons.ButtonDefaults
import com.kirson.baseproject.components.buttons.ButtonSize
import com.kirson.baseproject.components.buttons.ButtonSlot
import com.kirson.baseproject.core.ui.uikit.R
import com.kirson.baseproject.ui.theme.BaseProjectAppTheme

@Composable
fun SecondaryButtonSmall(
  modifier: Modifier = Modifier,
  onClick: () -> Unit,
  text: String,
  icon: @Composable (() -> Unit)? = null,
  slotPosition: ButtonSlot = ButtonSlot.None,
  isProgressBarVisible: Boolean = false,
  enabled: Boolean = true,
) {
  Button(
    modifier = modifier,
    onClick = onClick,
    text = text,
    icon = icon,
    slotPosition = slotPosition,
    isProgressBarVisible = isProgressBarVisible,
    enabled = enabled,
    size = ButtonSize.Small,
    colors = ButtonDefaults.secondaryButtonColors(),
  )
}

@Preview(showBackground = true, widthDp = 360)
@Composable
internal fun SortButtonPreview() {
  BaseProjectAppTheme {
    SecondaryButtonSmall(
      onClick = {},
      slotPosition = ButtonSlot.AfterText,
      icon = {
        Icon(
          painter = painterResource(id = R.drawable.ic_arrow_up_round_cap_16),
          contentDescription = "sort direction",
          tint = BaseProjectAppTheme.colors.black
        )
      },
      text = "text"
    )
  }
}
