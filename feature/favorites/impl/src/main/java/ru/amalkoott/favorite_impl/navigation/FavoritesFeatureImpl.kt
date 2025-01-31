package ru.amalkoott.favorite_impl.navigation

import android.annotation.SuppressLint
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.navArgument
import ru.amalkoott.common.components.VacancyScreen
import ru.amalkoott.favorite_api.FavoritesFeatureApi
import ru.amalkoott.favorite_impl.presentation.FavoritesScreen
import javax.inject.Inject

class FavoritesFeatureImpl @Inject constructor(
    override val name: String,
    override val route: String,
    override val startDestination: String,
    override val vacancyRoute: String,
    override val vacancyParameter: String
    ) : FavoritesFeatureApi {

    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun registerGraph(
        navGraphBuilder: NavGraphBuilder,
        navController: NavHostController,
        modifier: Modifier
    ) {
        navGraphBuilder.navigation(
            startDestination = startDestination,
            route = route
        ){
            var vacancyTitle : String = "" // служит лишь для заглушки, при навигации на экран вакансии (в будущем) можно достать детали по вакансии через id
            composable(route = startDestination) {
                FavoritesScreen(
                    name = name.toString(),
                    onClick = { vacancy ->
                        vacancyTitle = vacancy.title.toString()
                        navController.navigate("$vacancyRoute/${vacancy.id}")
                    },
                )
            }

            composable(
                route = "$vacancyRoute/{$vacancyParameter}",
                arguments = listOf(navArgument(vacancyParameter) { type = NavType.StringType })
            ) { backStackEntry ->
                VacancyScreen(vacancyTitle)
            }
        }

    }
}
