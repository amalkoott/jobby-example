package ru.amalkoott.core.di

import MessageFeatureImpl
import android.content.Context
import android.content.res.Resources
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import ru.amalkoott.search_api.SearchFeatureApi
import ru.amalkoott.favorite_impl.navigation.FavoritesFeatureImpl
import ru.amalkoott.favorite_api.FavoritesFeatureApi
import ru.amalkoott.login_api.LoginFeatureApi
import ru.amalkoott.login_impl.navigation.LoginFeatureImpl
import ru.amalkoott.message_api.MessageFeatureApi
import ru.amalkoott.profile_api.ProfileFeatureApi
import ru.amalkoott.profile_impl.ProfileFeatureImpl
import ru.amalkoott.response_api.ResponseFeatureApi
import ru.amalkoott.response_impl.ResponseFeatureImpl
import ru.amalkoott.search_impl.navigation.SearchFeatureImpl
import javax.inject.Singleton
import ru.amalkoott.common.R.string



@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideAppDestinations(
        @ApplicationContext context: Context
    ): AppDestination{

        val resource = context.resources

        return AppDestination(
            provideLoginFeature(resource),
            provideFavoritesFeature(resource),
            provideSearchFeature(resource),
            provideResponseFeature(resource),
            provideMessageFeature(resource),
            provideProfileFeature(resource)
        )
    }

    @Singleton
    @Provides
    fun provideLoginFeature(resources: Resources): LoginFeatureApi {
        return LoginFeatureImpl(
            route = resources.getString(string.LOGIN_ROUTE),
            toHome = resources.getString(string.SEARCH_ROUTE),
            toEmail = resources.getString(string.WITH_EMAIL_ROUTE),
            email = resources.getString(string.EMAIL_PARAMETER)
        )
    }
    @Singleton
    @Provides
    fun provideFavoritesFeature(resources: Resources): FavoritesFeatureApi {
        return FavoritesFeatureImpl(
            name = resources.getString(string.FAVORITES_SCREEN_NAME),
            route = resources.getString(string.FAVORITES_ROUTE),
            startDestination = resources.getString(string.FAVORITES_ROUTE_MAIN),
            vacancyRoute = resources.getString(string.SEARCH_ROUTE_VACANCY),
            vacancyParameter = resources.getString(string.VACANCY_PARAMETER)
        )
    }
    @Singleton
    @Provides
    fun provideSearchFeature(resources: Resources): SearchFeatureApi {
        return SearchFeatureImpl(
            name = resources.getString(string.SEARCH_SCREEN_NAME),
            route = resources.getString(string.SEARCH_ROUTE),
            startDestination = resources.getString(string.SEARCH_ROUTE_MAIN),
            vacancyRoute = resources.getString(string.SEARCH_ROUTE_VACANCY),
            vacancyParameter = resources.getString(string.VACANCY_PARAMETER)
        )
    }
    @Singleton
    @Provides
    fun provideResponseFeature(resources: Resources): ResponseFeatureApi {
        return ResponseFeatureImpl(
            name = resources.getString(string.RESPONSE_SCREEN_NAME),
            route = resources.getString(string.RESPONSE_ROUTE),
        )
    }
    @Singleton
    @Provides
    fun provideMessageFeature(resources: Resources): MessageFeatureApi {
        return MessageFeatureImpl(
            name = resources.getString(string.MESSAGES_SCREEN_NAME),
            route = resources.getString(string.MESSAGES_ROUTE),
        )
    }
    @Singleton
    @Provides
    fun provideProfileFeature(resources: Resources): ProfileFeatureApi {
        return ProfileFeatureImpl(
            name = resources.getString(string.PROFILE_SCREEN_NAME),
            route = resources.getString(string.PROFILE_ROUTE),
        )
    }
}
