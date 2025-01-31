package ru.amalkoott.core.di

import ru.amalkoott.favorite_api.FavoritesFeatureApi
import ru.amalkoott.login_api.LoginFeatureApi
import ru.amalkoott.message_api.MessageFeatureApi
import ru.amalkoott.profile_api.ProfileFeatureApi
import ru.amalkoott.response_api.ResponseFeatureApi
import ru.amalkoott.search_api.SearchFeatureApi
import javax.inject.Inject

class AppDestination @Inject constructor(
    val loginFeature: LoginFeatureApi,
    val favoritesFeature: FavoritesFeatureApi,
    val searchFeature: SearchFeatureApi,
    val responseFeature: ResponseFeatureApi,
    val messageFeature: MessageFeatureApi,
    val profileFeature: ProfileFeatureApi
)