package com.chitneev.graphicdrawer.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.chitneev.graphicdrawer.ui.screens.graph.GraphScreen
import com.chitneev.graphicdrawer.ui.screens.main.MainScreen

@Composable
fun GraphDrawerNavHost(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    NavHost(
        navController = navController,
        startDestination = Main.route,
        modifier = modifier
    ) {
        composable(route = Main.route) {
            MainScreen(onNavigateToGraphScreen = {
                navController.navigate(
                    route = Graph.route,
                )
            })
        }
        composable(
            route = Graph.route,
        ) {
            GraphScreen()
        }
    }
}