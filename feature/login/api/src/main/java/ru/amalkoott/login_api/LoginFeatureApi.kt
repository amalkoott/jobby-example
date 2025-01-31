package ru.amalkoott.login_api

import ru.amalkoott.core.featureapi.FeatureApi

interface LoginFeatureApi : FeatureApi {
    val route: String
    val toEmail: String
    val email: String
    val toHome: String
}