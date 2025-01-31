package ru.amalkoott.searchapi

import ru.amalkoott.core.featureapi.FeatureApi

interface SearchFeatureApi : FeatureApi {
    val route: String
}