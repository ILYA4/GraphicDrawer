package com.chitneev.graphicdrawer.navigation

import androidx.navigation.NavType
import androidx.navigation.navArgument

interface Destination {
    val route : String
}

object Main: Destination {
    override val route = "main"
}

object Graph: Destination {
    override val route: String = "graph"

    const val countPointTypeArg = "count_point_arg"
    val routeWithArgs = "$route/{$countPointTypeArg}"
    val arguments = listOf(
        navArgument(countPointTypeArg) { type = NavType.IntType }
    )
}