package com.kirson.baseproject.navigation

import com.kirson.baseproject.navigation.R.drawable

sealed class NavTarget(val route: String, val icon: Int) {
    object Main : NavTarget(ModuleRoutes.MainFeature.route, ModuleRoutes.MainFeature.icon)

    object RootModule : NavTarget(ModuleRoutes.RootModule.route, ModuleRoutes.RootModule.icon)
}

enum class ModuleRoutes(val route: String, val icon: Int) {
    MainFeature("Главное", drawable.ic_plot_up_40),
    RootModule("rootmodule", drawable.ic_plot_up_40),
}