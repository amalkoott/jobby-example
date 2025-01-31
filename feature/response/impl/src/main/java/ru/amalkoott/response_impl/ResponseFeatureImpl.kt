package ru.amalkoott.response_impl

import androidx.navigation.compose.composable
import ru.amalkoott.response_api.ResponseFeatureApi
import javax.inject.Inject

class ResponseFeatureImpl @Inject constructor(
    override val name: String,
    override val route: String
) : ResponseFeatureApi {
    override fun registerGraph(
        navGraphBuilder: androidx.navigation.NavGraphBuilder,
        navController: androidx.navigation.NavHostController,
        modifier: androidx.compose.ui.Modifier
    ) {
        navGraphBuilder.composable(route){
            ResponseScreen(name)
        }
    }
}