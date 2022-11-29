package com.kirson.ecommerceconcept.navigation

import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.kirson.ecommerceconcept.screens.cart.CartScreen
import com.kirson.ecommerceconcept.screens.cart.CartScreenViewModel
import com.kirson.ecommerceconcept.screens.details.DetailsScreen
import com.kirson.ecommerceconcept.screens.details.DetailsScreenViewModel
import com.kirson.ecommerceconcept.screens.explorer.ExplorerScreen
import com.kirson.ecommerceconcept.screens.explorer.ExplorerScreenViewModel

fun NavGraphBuilder.addMainFeatureGraph(
    popBackStack: () -> Unit,
    onPhoneDetails: () -> Unit,
    onCart: () -> Unit
) {
    navigation(
        startDestination = NavTarget.Main.route,
        route = NavTarget.RootModule.route
    ) {
        composable(NavTarget.Main.route) {

            val viewModel: ExplorerScreenViewModel = hiltViewModel()
            ExplorerScreen(viewModel, onPhoneDetails = onPhoneDetails)

        }
        composable(NavTarget.Details.route) {
            val viewModel: DetailsScreenViewModel = hiltViewModel()
            DetailsScreen(viewModel = viewModel, onBackScreen = popBackStack, onCartScreen = onCart)
        }
        composable(NavTarget.Cart.route) {
            val viewModel: CartScreenViewModel = hiltViewModel()
            CartScreen(viewModel = viewModel, onBackScreen = popBackStack)
        }
        composable(NavTarget.Shopping.route) {
            ShoppingFeatureView()
        }
        composable(NavTarget.Favourites.route) {
            FavouritesFeatureView()
        }
        composable(NavTarget.Profile.route) {
            ProfileFeatureView()
        }
    }


}