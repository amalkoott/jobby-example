package ru.amalkoott.profile_api

import ru.amalkoott.core.featureapi.FeatureApi
import ru.amalkoott.core.featureapi.ScreenApi

interface ProfileFeatureApi : FeatureApi, ScreenApi {
    val route : String
}