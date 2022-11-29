package com.kirson.ecommerceconcept.components

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.kirson.ecommerceconcept.ui.theme.EcommerceConceptAppTheme

@Composable
fun EmptyContentMessage(
  modifier: Modifier = Modifier,
  @DrawableRes
  imgRes: Int,
  title: String,
  description: String,
) {
  Column(
    modifier = modifier.padding(horizontal = 16.dp),
    horizontalAlignment = Alignment.CenterHorizontally,
  ) {
    Image(
      modifier = Modifier
        .size(170.dp),
      painter = painterResource(id = imgRes),
      contentDescription = "empty content image",
    )
    Text(
        text = title,
        color = EcommerceConceptAppTheme.colors.black,
        style = TextStyle(
            fontWeight = FontWeight.Medium,
            fontSize = 17.sp,
        ),
        textAlign = TextAlign.Center,
    )
    Text(
        modifier = Modifier.padding(top = 4.dp),
        text = description,
        color = EcommerceConceptAppTheme.colors.black,
        style = TextStyle(
            fontWeight = FontWeight.Normal,
            fontSize = 16.sp,
        ),
        textAlign = TextAlign.Center,
    )
  }
}
