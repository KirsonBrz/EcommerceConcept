package com.kirson.ecommerceconcept.components

import androidx.compose.animation.Crossfade
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.material.ContentAlpha
import androidx.compose.material.IconButton
import androidx.compose.material.LocalContentAlpha
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.kirson.ecommerceconcept.ui.theme.EcommerceConceptAppTheme


@Composable
fun CountSelector(
    count: Int,
    decreaseItemCount: () -> Unit,
    increaseItemCount: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(modifier = modifier) {
        CompositionLocalProvider(LocalContentAlpha provides ContentAlpha.medium) {
            Text(
                text = "kek",
                style = MaterialTheme.typography.subtitle1,
                color = EcommerceConceptAppTheme.colors.textSecondary,
                modifier = Modifier
                    .padding(end = 18.dp)
                    .align(Alignment.CenterVertically)
            )
        }
        IconButton(
            onClick = decreaseItemCount,

            modifier = Modifier.align(Alignment.CenterVertically)
        ) {
            Icon(
                imageVector = Icons.Default.Close,
                contentDescription = "decrease"
            )
        }
        Crossfade(
            targetState = count,
            modifier = Modifier
                .align(Alignment.CenterVertically)
        ) {
            Text(
                text = "$it",
                style = MaterialTheme.typography.subtitle2,
                fontSize = 18.sp,
                color = EcommerceConceptAppTheme.colors.textPrimary,
                textAlign = TextAlign.Center,
                modifier = Modifier.widthIn(min = 24.dp)
            )
        }
        IconButton(
            onClick = increaseItemCount,

            modifier = Modifier.align(Alignment.CenterVertically)
        ) {
            Icon(
                imageVector = Icons.Default.Add,
                contentDescription = "increase"
            )
        }
    }
}