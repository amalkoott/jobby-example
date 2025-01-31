package ru.amalkoott.search_api

import ru.amalkoott.core.featureapi.FeatureApi
import ru.amalkoott.core.featureapi.ScreenApi


interface SearchFeatureApi : FeatureApi, ScreenApi {
    val route: String
    val startDestination: String
    val vacancyRoute: String
    val vacancyParameter: String
}