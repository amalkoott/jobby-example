package ru.amalkoott.loginimpl.navigation

import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import ru.amalkoott.core.featureapi.FeatureApi
import ru.amalkoott.login.LoginFeatureApi
import ru.amalkoott.loginimpl.presentation.WithEmailScreen
import ru.amalkoott.loginimpl.presentation.LoginScreen
import javax.inject.Inject


class LoginFeatureImpl @Inject constructor(

//    override val smth: FeatureApi
//    override val homeRoute: String
) : LoginFeatureApi {
    override val route: String = "login"
    override fun registerGraph(
        navGraphBuilder: NavGraphBuilder,
        navController: NavHostController,
        modifier: Modifier
    ) {
        navGraphBuilder.composable(LoginScreen.Login.route) {
            LoginScreen(
                modifier = modifier,
                onWithEmail = {
                    //val loginFeature = AppModule.homeFeature()
                    navController.navigate(LoginScreen.WithEmail.route) {
                        popUpTo(route) { inclusive = true }
                    }
                },
                onWithPassword = {

                }
            )
        }
        navGraphBuilder.composable(LoginScreen.WithEmail.route) {
            WithEmailScreen (
                modifier = modifier,
                argument = "",
                onClick = {
                    //navController.navigate()
                    navController.navigate("favorites") {
                        popUpTo(route) { inclusive = true }
                    }
                }
            )
        }
    }
}
private sealed class LoginScreen(val route: String){
    object Login : LoginScreen(route = "LOGIN")
    object WithEmail : LoginScreen(route = "WITH_EMAIL")
    object WithPassword : LoginScreen(route = "WITH_PHONE")
}