package com.kirson.ecommerceconcept.components.buttons

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun SecondaryButton(
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
    size = ButtonSize.Normal,
    colors = ButtonDefaults.secondaryButtonColors(),
  )
}
