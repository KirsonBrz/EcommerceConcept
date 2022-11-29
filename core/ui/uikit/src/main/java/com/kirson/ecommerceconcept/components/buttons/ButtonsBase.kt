package com.kirson.ecommerceconcept.components.buttons

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.ButtonColors
import androidx.compose.material.ButtonElevation
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.LocalContentColor
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.kirson.ecommerceconcept.ui.theme.EcommerceConceptAppTheme
import com.kirson.ecommerceconcept.ui.theme.Shapes

enum class ButtonSlot {
  /**
   * Position a slot before text
   */
  BeforeText,

  /**
   * Position a slot after text
   */
  AfterText,

  /**
   * Position a slot in the end of the text
   */
  End,

  /**
   * No slot is reserved. Icon and progress bar related arguments are ignored in this mode.
   */
  None
}

internal enum class ButtonSize {
  Normal,
  Small,
}

@Suppress("LongMethod") // complex composable
@Composable
internal fun Button(
  modifier: Modifier = Modifier,
  onClick: () -> Unit,
  text: String,
  icon: @Composable (() -> Unit)? = null,
  slotPosition: ButtonSlot = ButtonSlot.End,
  isProgressBarVisible: Boolean = false,
  enabled: Boolean = true,
  size: ButtonSize = ButtonSize.Normal,
  colors: ButtonColors = ButtonDefaults.primaryButtonColors(),
  elevation: ButtonElevation = androidx.compose.material.ButtonDefaults
    .elevation(defaultElevation = 0.dp, pressedElevation = 0.dp),
) {
  return Button(
    modifier = modifier,
    onClick = onClick,
    icon = icon,
    slotPosition = slotPosition,
    isProgressBarVisible = isProgressBarVisible,
    enabled = enabled,
    size = size,
    colors = colors,
    elevation = elevation,
  ) {
    ButtonText(size, text)
  }
}

@Suppress("LongMethod") // complex composable
@Composable
internal fun Button(
  modifier: Modifier = Modifier,
  onClick: () -> Unit,
  icon: @Composable (() -> Unit)? = null,
  slotPosition: ButtonSlot = ButtonSlot.End,
  isProgressBarVisible: Boolean = false,
  enabled: Boolean = true,
  size: ButtonSize = ButtonSize.Normal,
  colors: ButtonColors = ButtonDefaults.primaryButtonColors(),
  elevation: ButtonElevation = androidx.compose.material.ButtonDefaults
    .elevation(defaultElevation = 0.dp, pressedElevation = 0.dp),
  content: @Composable RowScope.() -> Unit,
) {
  val iconPadding = when (size) {
    ButtonSize.Normal -> 8.dp
    ButtonSize.Small -> 4.dp
  }
  androidx.compose.material.Button(
    onClick,
    modifier.heightIn(
      min = when (size) {
        ButtonSize.Normal -> 56.dp
        ButtonSize.Small -> 40.dp
      }
    ),
    enabled,
    colors = colors,
    shape = size.toShape(),
    elevation = elevation
  ) {
    val contentColor by colors.contentColor(enabled = enabled)
    CompositionLocalProvider(LocalContentColor provides contentColor) {
      when (slotPosition) {
        ButtonSlot.None -> {
          content()
        }

        ButtonSlot.AfterText -> {
          content()
          SlotContent(
            modifier = Modifier.padding(start = iconPadding),
            icon = icon,
            isProgressBarVisible = isProgressBarVisible,
          )
        }

        ButtonSlot.BeforeText -> {
          SlotContent(
            modifier = Modifier.padding(end = iconPadding),
            icon = icon,
            isProgressBarVisible = isProgressBarVisible,
          )
          content()
        }

        ButtonSlot.End -> {
          // this is required to keep text horizontally centered
          SlotContent(
            modifier = Modifier
                .padding(end = iconPadding)
                .alpha(alpha = 0f),
            icon = icon,
            isProgressBarVisible = isProgressBarVisible,
          )
          Spacer(modifier = Modifier.weight(weight = 1f))
          content()
          Spacer(modifier = Modifier.weight(weight = 1f))
          SlotContent(
            modifier = Modifier.padding(start = iconPadding),
            icon = icon,
            isProgressBarVisible = isProgressBarVisible,
          )
        }
      }
    }
  }
}

internal fun ButtonSize.toShape(): Shape {
  return when (this) {
    ButtonSize.Normal -> Shapes.large2
    ButtonSize.Small -> Shapes.small
  }
}

@Composable
internal fun ButtonText(
  size: ButtonSize,
  text: String,
) {
  Text(
    textAlign = TextAlign.Center,
    style = when (size) {
        ButtonSize.Normal -> EcommerceConceptAppTheme.typography.button1
        ButtonSize.Small -> EcommerceConceptAppTheme.typography.button2
    },
    text = text,
  )
}

@Composable
private fun SlotContent(
  modifier: Modifier,
  icon: @Composable (() -> Unit)?,
  isProgressBarVisible: Boolean,
) {
  if (icon == null && !isProgressBarVisible) {
    Box(modifier = modifier.size(24.dp))
  } else {
    Box(
      modifier = modifier,
      contentAlignment = Alignment.Center
    ) {
      if (icon != null && !isProgressBarVisible) {
        icon()
      }
      if (isProgressBarVisible) {
        CircularProgressIndicator(
          modifier = Modifier.size(24.dp),
          color = LocalContentColor.current,
          strokeWidth = 2.dp
        )
      }
    }
  }
}

internal object ButtonDefaults {
  @Composable
  fun primaryButtonColors(): ButtonColors {
    return ButtonColors(
        backgroundColor = Color.Transparent,
        contentColor = EcommerceConceptAppTheme.colors.primaryButton,
        disabledBackgroundColor = EcommerceConceptAppTheme.colors.textTertiary,
        disabledContentColor = EcommerceConceptAppTheme.colors.textTertiary,
    )
  }

  @Composable
  fun secondaryButtonColors(): ButtonColors {
    return ButtonColors(
        backgroundColor = EcommerceConceptAppTheme.colors.contendSecondary,
        contentColor = EcommerceConceptAppTheme.colors.textPrimary,
        disabledBackgroundColor = EcommerceConceptAppTheme.colors.contendSecondary,
        disabledContentColor = EcommerceConceptAppTheme.colors.textTertiary,
    )
  }

  @Composable
  fun tertiaryButtonColors(): ButtonColors {
    return ButtonColors(
        backgroundColor = EcommerceConceptAppTheme.colors.contendSecondary,
        contentColor = EcommerceConceptAppTheme.colors.textPrimary,
        disabledBackgroundColor = EcommerceConceptAppTheme.colors.contendSecondary,
        disabledContentColor = EcommerceConceptAppTheme.colors.textTertiary,
    )
  }

  @Composable
  fun quaternaryButtonColors(): ButtonColors {
    return ButtonColors(
        backgroundColor = Color.Transparent,
        contentColor = EcommerceConceptAppTheme.colors.textPrimary,
        disabledBackgroundColor = Color.Transparent,
        disabledContentColor = EcommerceConceptAppTheme.colors.textTertiary,
    )
  }

  @Composable
  fun positiveButtonColors(): ButtonColors {
    return ButtonColors(
        backgroundColor = Color.Transparent,
        contentColor = EcommerceConceptAppTheme.colors.textPrimary,
        disabledBackgroundColor = EcommerceConceptAppTheme.colors.contendTertiary,
        disabledContentColor = EcommerceConceptAppTheme.colors.textTertiary,
    )
  }

  @Composable
  fun negativeButtonColors(): ButtonColors {
    return ButtonColors(
        backgroundColor = Color.Transparent,
        contentColor = EcommerceConceptAppTheme.colors.textPrimary,
        disabledBackgroundColor = EcommerceConceptAppTheme.colors.contendTertiary,
        disabledContentColor = EcommerceConceptAppTheme.colors.textTertiary,
    )
  }

  @Composable
  fun topAppBarButtonColors(
      contentColor: Color = EcommerceConceptAppTheme.colors.textPrimary,
  ): ButtonColors {
    return ButtonColors(
        backgroundColor = Color.Transparent,
        contentColor = contentColor,
        disabledBackgroundColor = Color.Transparent,
        disabledContentColor = EcommerceConceptAppTheme.colors.textTertiary,
    )
  }

  @Composable
  fun selectedChipButtonColors(): ButtonColors {
    return ButtonColors(
        backgroundColor = EcommerceConceptAppTheme.colors.contendSecondary,
        contentColor = EcommerceConceptAppTheme.colors.textPrimary,
        disabledBackgroundColor = EcommerceConceptAppTheme.colors.contendTertiary,
        disabledContentColor = EcommerceConceptAppTheme.colors.textTertiary,
    )
  }

  @Composable
  fun unselectedChipButtonColors(): ButtonColors {
    return ButtonColors(
        backgroundColor = Color.Transparent,
        contentColor = EcommerceConceptAppTheme.colors.textSecondary,
        disabledBackgroundColor = Color.Transparent,
        disabledContentColor = EcommerceConceptAppTheme.colors.textTertiary,
    )
  }
}

@Immutable
data class ButtonColors(
  private val backgroundColor: Color,
  private val contentColor: Color,
  private val disabledBackgroundColor: Color,
  private val disabledContentColor: Color,
) : ButtonColors {

  @Composable
  override fun backgroundColor(enabled: Boolean): State<Color> {
    return rememberUpdatedState(if (enabled) backgroundColor else disabledBackgroundColor)
  }

  @Composable
  override fun contentColor(enabled: Boolean): State<Color> {
    return rememberUpdatedState(if (enabled) contentColor else disabledContentColor)
  }
}