package com.kirson.baseproject.components

import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.kirson.baseproject.navigation.NavTarget
import com.kirson.baseproject.ui.theme.BaseProjectAppTheme

@Composable
fun BottomNavigationBar(navController: NavController) {
  val items = listOf(
    NavTarget.Main
  )
  BottomNavigation(
    backgroundColor = BaseProjectAppTheme.colors.contendAccentTertiary,
    contentColor = BaseProjectAppTheme.colors.contendPrimary
  ) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route
    items.forEach { item ->
      BottomNavigationItem(
        icon = { Icon(painterResource(id = item.icon), contentDescription = item.route) },
        label = { Text(text = item.route) },
        selectedContentColor = Color.White,
        unselectedContentColor = Color.White.copy(0.4f),
        alwaysShowLabel = true,
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
        }
      )
    }
  }
}
