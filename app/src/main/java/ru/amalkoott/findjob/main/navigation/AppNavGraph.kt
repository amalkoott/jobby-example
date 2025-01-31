package ru.amalkoott.findjob.main.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import ru.amalkoott.core.di.AppDestination
import ru.amalkoott.core.featureapi.register

@Composable
fun AppNavGraph(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    destinations: AppDestination
){
    NavHost(
        navController = navController,
        startDestination = destinations.loginFeature.route
    ) {
        register(
            destinations.loginFeature,
            navController = navController,
            modifier = modifier
        )
        register(
            destinations.favoritesFeature,
            navController = navController,
            modifier = modifier
        )
        register(
            destinations.searchFeature,
            navController = navController,
            modifier = modifier
        )
        register(
            destinations.responseFeature,
            navController = navController,
            modifier = modifier
        )
        register(
            destinations.messageFeature,
            navController = navController,
            modifier = modifier
        )
        register(
            destinations.profileFeature,
            navController = navController,
            modifier = modifier
        )
    }
}
