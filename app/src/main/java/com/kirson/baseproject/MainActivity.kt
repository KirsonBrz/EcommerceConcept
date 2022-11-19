package com.kirson.baseproject

import NavigationComponent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.kirson.baseproject.components.BottomNavigationBar
import com.kirson.baseproject.navigation.LandingScreen
import com.kirson.baseproject.navigation.Navigator
import com.kirson.baseproject.ui.theme.BaseProjectAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BaseProjectAppTheme {

                var showLandingScreen by remember { mutableStateOf(true) }
                if (showLandingScreen) {
                    Surface(
                        color = BaseProjectAppTheme.colors.backgroundSecondary,
                        modifier = Modifier.fillMaxSize()
                    ) {
                        LandingScreen(onTimeout = { showLandingScreen = false })
                    }
                } else {
                    MainScreenContent()
                }
            }
        }
    }
}


@Composable
fun MainScreenContent() {
    val navController = rememberNavController()
    val navigator = Navigator()
    Scaffold(
        backgroundColor = BaseProjectAppTheme.colors.backgroundPrimary,
        topBar = {
        },
        bottomBar = { BottomNavigationBar(navController) },
        content = { padding ->
            Box(
                modifier = Modifier
                    .padding(padding)
            )
            {
                NavigationComponent(navController, navigator)
            }
        }
    )
}



