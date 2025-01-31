package ru.amalkoott.findjob.main.presentation

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.WindowInsetsSides
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.only
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBars
import androidx.compose.material.Scaffold
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import ru.amalkoott.core.di.AppDestination
import ru.amalkoott.findjob.main.navigation.AppNavGraph
import ru.amalkoott.findjob.ui.theme.FindJobTheme
import ru.amalkoott.navigationbar.BottomBar


@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun AppContent(appDestination: AppDestination) {
    FindJobTheme(darkTheme = true) {
        val navController = rememberNavController()

        Scaffold(
            backgroundColor = colorScheme.background,
            modifier = Modifier.padding(WindowInsets.systemBars.only(WindowInsetsSides.Vertical).asPaddingValues()),
            bottomBar = { BottomBar(navController) }
        ) {
            AppNavGraph(
                navController = navController,
                modifier = Modifier,
                destinations = appDestination
            )
        }
    }

}

