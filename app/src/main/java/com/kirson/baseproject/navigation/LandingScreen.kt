package com.kirson.baseproject.navigation

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
import com.kirson.baseproject.ui.theme.BaseProjectAppTheme
import kotlinx.coroutines.delay

@Composable
fun LandingScreen(modifier: Modifier = Modifier, onTimeout: () -> Unit) {
    Box(
        modifier = modifier.fillMaxSize()
    )
    {
        // This will always refer to the latest onTimeout function that
        // LandingScreen was recomposed with
        val currentOnTimeout by rememberUpdatedState(onTimeout)

        // Create an effect that matches the lifecycle of LandingScreen.
        // If LandingScreen recomposes or onTimeout changes,
        // the delay shouldn't start again.


        Surface(
            color = BaseProjectAppTheme.colors.mainColor,
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
            delay(4000)
            currentOnTimeout()
        }

    }
}