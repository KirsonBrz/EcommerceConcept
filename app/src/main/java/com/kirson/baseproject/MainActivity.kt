package com.kirson.baseproject

import NavigationComponent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.kirson.baseproject.components.BottomNavigationBar
import com.kirson.baseproject.navigation.Navigator
import com.kirson.baseproject.ui.theme.BaseProjectAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BaseProjectAppTheme {
                MainScreenContent()
            }
        }
    }
}



@Composable
fun MainScreenContent() {
    val navController = rememberNavController()
    val navigator = Navigator()
    Scaffold(
        topBar = {
        },
        bottomBar = { BottomNavigationBar(navController) },
        content = { padding ->
            Box(modifier = Modifier.padding(padding)) {
                NavigationComponent(navController, navigator)
            }
        }
    )
}

