package ru.amalkoott.response_api

import ru.amalkoott.core.featureapi.FeatureApi
import ru.amalkoott.core.featureapi.ScreenApi

interface ResponseFeatureApi : FeatureApi, ScreenApi {
    val route: String
}