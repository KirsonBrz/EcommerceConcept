package com.kirson.baseproject.navigation

import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.kirson.baseproject.MainFeatureView
import com.kirson.baseproject.MainFeatureViewModel

fun NavGraphBuilder.addMainFeatureGraph(popBackStack: () -> Unit) {
    navigation(
        startDestination = NavTarget.Main.route,
        route = NavTarget.RootModule.route
    ) {
        composable(NavTarget.Main.route) {
            val viewModel: MainFeatureViewModel = hiltViewModel()
            MainFeatureView(viewModel)
        }
        composable(NavTarget.Cart.route) {
            CartFeatureView()
        }
        composable(NavTarget.Favourites.route) {
            FavouritesFeatureView()
        }
        composable(NavTarget.Profile.route) {
            ProfileFeatureView()
        }
    }
}