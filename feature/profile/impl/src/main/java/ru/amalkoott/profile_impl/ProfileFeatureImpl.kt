package ru.amalkoott.profile_impl

import androidx.navigation.compose.composable
import ru.amalkoott.profile_api.ProfileFeatureApi
import javax.inject.Inject

class ProfileFeatureImpl @Inject constructor(
    override val name: String,
    override val route: String
) : ProfileFeatureApi {
    override fun registerGraph(
        navGraphBuilder: androidx.navigation.NavGraphBuilder,
        navController: androidx.navigation.NavHostController,
        modifier: androidx.compose.ui.Modifier
    ) {
        navGraphBuilder.composable(route){
            ProfileScreen(name)
        }
    }
}