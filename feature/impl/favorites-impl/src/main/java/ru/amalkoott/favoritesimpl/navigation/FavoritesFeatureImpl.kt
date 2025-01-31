package ru.amalkoott.favoritesimpl.navigation

import android.annotation.SuppressLint
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import ru.amalkoott.favoritesimpl.presentation.FavoritesScreen
import ru.amalkoott.favoritesapi.FavoritesFeatureApi
import javax.inject.Inject

class FavoritesFeatureImpl @Inject constructor() : FavoritesFeatureApi {

    override val route: String = "favorites"

    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun registerGraph(
        navGraphBuilder: NavGraphBuilder,
        navController: NavHostController,
        modifier: Modifier
    ) {
        navGraphBuilder.composable(route) {
               FavoritesScreen(
                   name = HomeScreen.Favorites.route,
                   onClick = { },
               )
           }

    }
}
internal sealed class HomeScreen(val route: String){
    object Home : HomeScreen(route = "home")
    object Search : HomeScreen(route = "search")
    object Favorites : HomeScreen(route = "favorites")
    object Message : HomeScreen(route = "message")
    object Response : HomeScreen(route = "response")
    object Profile : HomeScreen(route = "profile")
}