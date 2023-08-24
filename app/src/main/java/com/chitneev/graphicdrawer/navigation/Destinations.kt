package com.chitneev.graphicdrawer.navigation


interface Destination {
    val route : String
}

object Main: Destination {
    override val route = "main"
}

object Graph: Destination {
    override val route: String = "graph"
}