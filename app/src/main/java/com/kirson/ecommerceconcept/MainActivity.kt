package com.kirson.ecommerceconcept


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
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.kirson.ecommerceconcept.components.BottomNavigationBar
import com.kirson.ecommerceconcept.navigation.InitialScreen
import com.kirson.ecommerceconcept.navigation.NavTarget
import com.kirson.ecommerceconcept.navigation.Navigator
import com.kirson.ecommerceconcept.ui.theme.EcommerceConceptAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            EcommerceConceptAppTheme {

                var showInitialScreen by rememberSaveable { mutableStateOf(true) }
                if (showInitialScreen) {
                    Surface(
                        color = EcommerceConceptAppTheme.colors.backgroundSecondary,
                        modifier = Modifier.fillMaxSize()
                    ) {
                        InitialScreen(onTimeout = { showInitialScreen = false })
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

    var bottomBarState by rememberSaveable { mutableStateOf(true) }
    val navBackStackEntry by navController.currentBackStackEntryAsState()

    bottomBarState = when (navBackStackEntry?.destination?.route) {
        NavTarget.Details.route -> false // on this screen bottom bar should be hidden
        NavTarget.Cart.route -> false // on this screen bottom bar should be hidden
        else -> true // in all other cases show bottom bar
    }

    Scaffold(
        backgroundColor = EcommerceConceptAppTheme.colors.backgroundPrimary,
        topBar = {
        },
        bottomBar = {
            BottomNavigationBar(
                navController,
                bottomBarState
            )
        },
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



