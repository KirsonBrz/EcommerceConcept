package com.kirson.baseproject.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.kirson.baseproject.MainFeatureView

fun NavGraphBuilder.addMainFeatureGraph(popBackStack: () -> Unit) {
    navigation(
        startDestination = NavTarget.Main.route,
        route = NavTarget.RootModule.route
    ) {
        composable(NavTarget.Main.route) {
            MainFeatureView()
        }
    }
}