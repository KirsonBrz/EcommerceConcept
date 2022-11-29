package com.kirson.ecommerceconcept.navigation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.kirson.ecommerceconcept.ui.theme.EcommerceConceptAppTheme
import kotlinx.coroutines.delay

@Composable
fun InitialScreen(modifier: Modifier = Modifier, onTimeout: () -> Unit) {
    Box(
        modifier = modifier.fillMaxSize()
    )
    {

        val currentOnTimeout by rememberUpdatedState(onTimeout)



        Surface(
            color = EcommerceConceptAppTheme.colors.primaryColor,
            modifier = Modifier
                .size(132.dp)
                .clip(CircleShape)
                .align(Alignment.Center)

        ) {


        }
        Text(
            text = "Ecommerce \nConcept",
            fontSize = 30.sp,
            fontWeight = FontWeight.W800,
            color = Color.White,
            textAlign = TextAlign.Start,
            modifier = Modifier
                .align(Alignment.Center)
                .padding(start = 100.dp)

        )



        LaunchedEffect(true) {
            delay(1300)
            currentOnTimeout()
        }

    }
}