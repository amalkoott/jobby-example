package ru.amalkoott.login_impl.navigation

import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import ru.amalkoott.login_api.LoginFeatureApi
import ru.amalkoott.login_impl.presentation.LoginScreen
import ru.amalkoott.login_impl.presentation.components.WithEmailScreen
import javax.inject.Inject

class LoginFeatureImpl @Inject constructor(
    override val route: String,
    override val toEmail: String,
    override val email: String,
    override val toHome: String) : LoginFeatureApi {
    override fun registerGraph(
        navGraphBuilder: NavGraphBuilder,
        navController: NavHostController,
        modifier: Modifier
    ) {
        navGraphBuilder.composable(route) {
            LoginScreen(
                modifier = modifier,
                onWithEmail = { emailValue ->
                    navController.navigate("$toEmail/$emailValue"){
                        popUpTo(route) { inclusive = true }
                    }
                },
                onWithPassword = {

                }
            )
        }
        navGraphBuilder.composable(
            route = "$toEmail/{$email}",
            arguments = listOf(navArgument(email) { type = NavType.StringType }))
        { backStackEntry ->
            val arg = backStackEntry.arguments?.getString(email)
            WithEmailScreen (
                modifier = modifier,
                argument = arg.toString(),
                onClick = {
                    navController.navigate(toHome) {
                        popUpTo(route) { inclusive = true }
                    }
                }
            )
        }
    }
}