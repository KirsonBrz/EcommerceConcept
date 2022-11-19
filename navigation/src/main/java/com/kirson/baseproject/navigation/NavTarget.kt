package com.kirson.baseproject.navigation

import com.kirson.baseproject.navigation.R.drawable

sealed class NavTarget(val route: String, val icon: Int) {
    object Main : NavTarget(ModuleRoutes.MainFeature.route, ModuleRoutes.MainFeature.icon)
    object Cart : NavTarget(ModuleRoutes.CartFeature.route, ModuleRoutes.CartFeature.icon)
    object Favourites :
        NavTarget(ModuleRoutes.FavouritesFeature.route, ModuleRoutes.FavouritesFeature.icon)

    object Profile : NavTarget(ModuleRoutes.ProfileFeature.route, ModuleRoutes.ProfileFeature.icon)

    object RootModule : NavTarget(ModuleRoutes.RootModule.route, ModuleRoutes.RootModule.icon)
}

enum class ModuleRoutes(val route: String, val icon: Int) {
    MainFeature("Explorer", drawable.main_24),
    CartFeature("My Cart", drawable.cart_24),
    FavouritesFeature("Favourites", drawable.favorite_24),
    ProfileFeature("Profile", drawable.profile_24),
    RootModule("rootmodule", drawable.main_24),
}