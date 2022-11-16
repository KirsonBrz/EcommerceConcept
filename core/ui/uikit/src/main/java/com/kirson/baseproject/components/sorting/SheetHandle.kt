package com.kirson.baseproject.components.sorting

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.kirson.baseproject.core.ui.uikit.R
import com.kirson.baseproject.ui.theme.BaseProjectAppTheme

@Composable
fun SheetHandle(modifier: Modifier = Modifier) {
  Box(
    modifier = modifier
      .height(24.dp)
      .fillMaxWidth(),
    contentAlignment = Alignment.Center
  ) {
    Icon(
      painter = painterResource(id = R.drawable.ic_bottom_sheet_handle_36x4),
      tint = BaseProjectAppTheme.colors.contendSecondary,
      contentDescription = null
    )
  }
}
