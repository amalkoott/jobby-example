package ru.amalkoott.search_impl.navigation

import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.navArgument
import ru.amalkoott.search_api.SearchFeatureApi
import ru.amalkoott.search_impl.presentation.SearchScreen
import ru.amalkoott.search_impl.presentation.VacancyScreen
import javax.inject.Inject

class SearchFeatureImpl @Inject constructor(
    override val name: String,
    override val route: String,
    override val startDestination: String,
    override val vacancyRoute: String,
    override val vacancyParameter: String

): SearchFeatureApi {

    override fun registerGraph(
        navGraphBuilder: androidx.navigation.NavGraphBuilder,
        navController: androidx.navigation.NavHostController,
        modifier: androidx.compose.ui.Modifier
    ) {
        navGraphBuilder.navigation(
            startDestination = startDestination,
            route = route
        ){
            var vacancyTitle : String = "" // служит лишь для заглушки, при навигации на экран вакансии (в будущем) можно достать детали по вакансии через id
            composable(route = startDestination) {
                SearchScreen{ vacancy ->
                    vacancyTitle = vacancy.title.toString()
                    navController.navigate("$vacancyRoute/${vacancy.id}")
                }
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