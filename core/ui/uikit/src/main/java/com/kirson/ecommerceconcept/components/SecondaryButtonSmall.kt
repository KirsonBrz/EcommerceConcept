package com.kirson.ecommerceconcept.components

import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.kirson.ecommerceconcept.components.buttons.Button
import com.kirson.ecommerceconcept.components.buttons.ButtonDefaults
import com.kirson.ecommerceconcept.components.buttons.ButtonSize
import com.kirson.ecommerceconcept.components.buttons.ButtonSlot
import com.kirson.ecommerceconcept.core.ui.uikit.R
import com.kirson.ecommerceconcept.ui.theme.EcommerceConceptAppTheme

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
    EcommerceConceptAppTheme {
        SecondaryButtonSmall(
            onClick = {},
            slotPosition = ButtonSlot.AfterText,
            icon = {
                Icon(
                    painter = painterResource(id = R.drawable.ic_arrow_up_round_cap_16),
                    contentDescription = "sort direction",
                    tint = EcommerceConceptAppTheme.colors.black
                )
            },
      text = "text"
    )
  }
}
