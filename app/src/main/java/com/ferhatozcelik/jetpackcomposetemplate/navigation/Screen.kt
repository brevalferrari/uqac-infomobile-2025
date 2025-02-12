package com.ferhatozcelik.jetpackcomposetemplate.navigation

sealed class Screen(val route: String) {
    data object Main : Screen("main_screen")
    data object Detail : Screen("detail_screen")
}
