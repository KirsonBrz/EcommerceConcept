package com.kirson.ecommerceconcept.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.kirson.ecommerceconcept.core.ui.uikit.R
import com.kirson.ecommerceconcept.ui.theme.EcommerceConceptAppTheme

@Composable
fun Spinner(
  modifier: Modifier = Modifier,
  dropDownModifier: Modifier = Modifier,
  onItemSelected: (String) -> Unit,
  dropdownItemFactory: @Composable (String, Int) -> Unit,
  selectedText: String,
  options: List<String>
) {
  var expanded: Boolean by remember { mutableStateOf(false) }

  Box(modifier = modifier.wrapContentSize(Alignment.TopStart)) {
    Row(
      modifier = modifier
          .clickable { expanded = true }
          .padding(8.dp)
          .wrapContentSize(),
      verticalAlignment = Alignment.CenterVertically
    ) {
      Text(selectedText)
      Icon(
        painter = painterResource(id = R.drawable.ic_arrows_up_down_selector_left_16),
        contentDescription = "select base currency"

      )
    }
    DropdownMenu(
      expanded = expanded,
      onDismissRequest = { expanded = false },
        modifier = dropDownModifier.background(color = EcommerceConceptAppTheme.colors.backgroundSecondary)
    ) {
      options.forEachIndexed { index, element ->
        DropdownMenuItem(
            modifier = Modifier.background(color = EcommerceConceptAppTheme.colors.backgroundSecondary),
            onClick = {
                onItemSelected(options[index])
                expanded = false
            }) {
          dropdownItemFactory(element, index)

        }
      }
    }
  }
}
