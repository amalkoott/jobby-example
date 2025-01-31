package ru.amalkoott.searchimpl

import androidx.navigation.compose.composable
import ru.amalkoott.searchapi.SearchFeatureApi
import javax.inject.Inject

class SearchFeatureImpl constructor(): SearchFeatureApi {
    override val route: String = "search"
    override fun registerGraph(
        navGraphBuilder: androidx.navigation.NavGraphBuilder,
        navController: androidx.navigation.NavHostController,
        modifier: androidx.compose.ui.Modifier
    ) {
        navGraphBuilder.composable(route) {
            SearchScreen()
        }
    }
}