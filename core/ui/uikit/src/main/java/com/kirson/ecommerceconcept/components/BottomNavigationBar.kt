package com.kirson.ecommerceconcept.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.kirson.ecommerceconcept.navigation.NavTarget
import com.kirson.ecommerceconcept.ui.theme.EcommerceConceptAppTheme

@Composable
fun BottomNavigationBar(
    navController: NavController,
    bottomBarState: Boolean
) {
    val items = listOf(
        NavTarget.Main,
        NavTarget.Shopping,
        NavTarget.Favourites,
        NavTarget.Profile
    )
    AnimatedVisibility(
        visible = bottomBarState,
        enter = slideInVertically(initialOffsetY = { it }),
        exit = slideOutVertically(targetOffsetY = { it }),
        content = {
            Card(shape = MaterialTheme.shapes.large) {
                BottomNavigation(
                    backgroundColor = EcommerceConceptAppTheme.colors.bottomMenuBackground,
                    contentColor = EcommerceConceptAppTheme.colors.contendPrimary,
                ) {
                    val navBackStackEntry by navController.currentBackStackEntryAsState()
                    val currentRoute = navBackStackEntry?.destination?.route
                    items.forEach { item ->
                        BottomNavigationItem(icon = {
                            Icon(
                                painterResource(id = item.icon), contentDescription = item.route
                            )
                        },
                            label = { Text(text = item.route) },
                            selectedContentColor = Color.White,
                            unselectedContentColor = Color.White.copy(0.4f),
                            alwaysShowLabel = false,
                            selected = currentRoute == item.route,
                            onClick = {
                                navController.navigate(item.route) {
                                    navController.graph.startDestinationRoute?.let { route ->
                                        popUpTo(route) {
                                            saveState = true
                                        }
                                    }
                                    launchSingleTop = true
                                    restoreState = true
                                }
                            })
                    }
                }
            }
        })


}
