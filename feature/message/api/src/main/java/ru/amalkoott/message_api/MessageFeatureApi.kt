package ru.amalkoott.message_api

import ru.amalkoott.core.featureapi.FeatureApi
import ru.amalkoott.core.featureapi.ScreenApi

interface MessageFeatureApi : FeatureApi, ScreenApi {
    val route: String
}