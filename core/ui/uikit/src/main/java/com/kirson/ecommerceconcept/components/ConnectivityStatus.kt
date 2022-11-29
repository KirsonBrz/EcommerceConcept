package com.kirson.ecommerceconcept.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.expandVertically
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.kirson.ecommerceconcept.core.ui.uikit.R
import kotlinx.coroutines.delay

@Composable
fun ConnectivityStatus(
    isConnected: Boolean,
    onBackOnline: () -> Unit,
    modifier: Modifier = Modifier
) {
    var visibility by remember { mutableStateOf(false) }

    AnimatedVisibility(
        visible = visibility,
        enter = expandVertically(),
        exit = shrinkVertically()
    ) {
        ConnectivityStatusBox(isConnected = isConnected, modifier = modifier)
    }

    LaunchedEffect(isConnected) {
        if (!isConnected) {
            visibility = true
        } else {
            delay(2000)
            visibility = false
            onBackOnline()
        }
    }


}

@Composable
fun ConnectivityStatusBox(
    isConnected: Boolean,
    modifier: Modifier = Modifier
) {
    val backgroundColor by animateColorAsState(if (isConnected) Color.Green else Color.Red)
    val message = if (isConnected) "Back Online!" else "No Internet Connection!"
    val iconResource = if (isConnected) {
        R.drawable.ic_internet_available
    } else {
        R.drawable.ic_internet_unavailable
    }

    Box(
        modifier = modifier
            .background(backgroundColor)
            .fillMaxWidth()
            .padding(8.dp),
        contentAlignment = Alignment.Center
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(painterResource(id = iconResource), "Connectivity Icon", tint = Color.White)
            Spacer(modifier = Modifier.size(8.dp))
            Text(message, color = Color.White, fontSize = 15.sp)
        }
    }
}