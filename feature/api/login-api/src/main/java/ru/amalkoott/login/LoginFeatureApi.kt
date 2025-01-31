package ru.amalkoott.login

import ru.amalkoott.core.featureapi.FeatureApi

interface LoginFeatureApi : FeatureApi {
    val route: String
    //val smth: FeatureApi
// val homeRoute: String
}