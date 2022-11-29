package com.kirson.ecommerceconcept.navigation

sealed class NavTarget(val route: String, val icon: Int) {
    object Main :
        NavTarget(ModuleRoutes.MainFeature.route, ModuleRoutes.MainFeature.icon)

    object Details :
        NavTarget(ModuleRoutes.DetailsFeature.route, ModuleRoutes.DetailsFeature.icon)

    object Cart :
        NavTarget(ModuleRoutes.CartFeature.route, ModuleRoutes.CartFeature.icon)

    object Shopping :
        NavTarget(ModuleRoutes.ShoppingFeature.route, ModuleRoutes.ShoppingFeature.icon)


    object Favourites :
        NavTarget(ModuleRoutes.FavouritesFeature.route, ModuleRoutes.FavouritesFeature.icon)

    object Profile :
        NavTarget(ModuleRoutes.ProfileFeature.route, ModuleRoutes.ProfileFeature.icon)

    object RootModule :
        NavTarget(ModuleRoutes.RootModule.route, ModuleRoutes.RootModule.icon)
}

enum class ModuleRoutes(val route: String, val icon: Int) {
    MainFeature(
        "Explorer",
        R.drawable.main_24
    ),
    DetailsFeature(
        "Product Details",
        R.drawable.main_24
    ),
    CartFeature(
        "My Cart",
        R.drawable.main_24
    ),
    ShoppingFeature(
        "Shopping",
        R.drawable.cart_24
    ),
    FavouritesFeature(
        "Favourites",
        R.drawable.favorite_24
    ),
    ProfileFeature(
        "Profile",
        R.drawable.profile_24
    ),
    RootModule(
        "rootmodule",
        R.drawable.main_24
    ),
}