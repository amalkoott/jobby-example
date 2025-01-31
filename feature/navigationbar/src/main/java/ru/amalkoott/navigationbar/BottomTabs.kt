package ru.amalkoott.navigationbar

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import ru.amalkoott.common.R

enum class BottomTabs(
    @StringRes
    val route: Int,
    @DrawableRes
    val icon: Int,
    @StringRes
    val title: Int
) {
    SEARCH(
        R.string.SEARCH_ROUTE,
        R.drawable.ic_launcher_navigation_search_foreground,
        R.string.SEARCH_SCREEN_NAME
    ),
    FAVORITES(
        R.string.FAVORITES_ROUTE,
        R.drawable.ic_launcher_navigation_favorite_foreground,
        R.string.FAVORITES_SCREEN_NAME
    ),
    RESPONSE(
        R.string.RESPONSE_ROUTE,
        R.drawable.ic_launcher_navigation_response_foreground,
        R.string.RESPONSE_SCREEN_NAME
    ),
    MESSAGES(
        R.string.MESSAGES_ROUTE,
        R.drawable.ic_launcher_navigation_response_foreground,
        R.string.MESSAGES_SCREEN_NAME
    ),
    PROFILE(
        R.string.PROFILE_ROUTE,
        R.drawable.ic_launcher_navigation_profile_foreground,
        R.string.PROFILE_SCREEN_NAME
    )
}