package ru.amalkoott.favorite_api

import ru.amalkoott.core.featureapi.FeatureApi
import ru.amalkoott.core.featureapi.ScreenApi

interface FavoritesFeatureApi : FeatureApi, ScreenApi {

    val route: String
    val startDestination: String
    val vacancyRoute: String
    val vacancyParameter: String
}