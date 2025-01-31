package ru.amalkoott.navigationbar

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.zIndex
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.currentBackStackEntryAsState
import kotlinx.coroutines.flow.StateFlow
import ru.amalkoott.common.R

@Composable
fun BottomBar(
    navController: NavController,
    viewModel : BottomBarViewModel = hiltViewModel()){

    val counter:(BottomTabs) -> StateFlow<Int>? = { item -> viewModel.getCounter(item) }
    val tabs = remember { BottomTabs.entries.toTypedArray() }

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    val isLoginScreen = currentDestination?.route == stringResource(R.string.LOGIN_ROUTE)
            || currentDestination?.route == stringResource(R.string.WITH_EMAIL_ROUTE)

    if (!isLoginScreen) {
        Surface(
            modifier = Modifier
                .shadow(
                    elevation = dimensionResource(R.dimen.bottom_bar_elevation),
                    ambientColor = Color.White,
                    shape = RectangleShape
                )
        )
        {
            BottomNavigation(
                backgroundColor = MaterialTheme.colorScheme.background,
                modifier = Modifier
                    .height(dimensionResource(R.dimen.bottom_bar_heigth))
            ) {
                tabs.forEach { screen ->
                    AddItem(
                        screen = screen,
                        currentDestination = currentDestination,
                        navController = navController,
                        bubble = counter(screen)
                    )
                }
            }
        }


    }
}

@SuppressLint("StateFlowValueCalledInComposition", "SuspiciousIndentation")
@Composable
fun RowScope.AddItem(
    screen: BottomTabs,
    currentDestination: NavDestination?,
    navController: NavController,
    bubble: StateFlow<Int>?
) {
    val screenRoute = stringResource(screen.route)
    val bubbleValue = bubble?.collectAsState()
    val color = if(screenRoute != currentDestination?.route) MaterialTheme.colorScheme.onSurfaceVariant else MaterialTheme.colorScheme.secondary
    BottomNavigationItem(
        label = {
            Text(
                text = stringResource(screen.title),
                style = MaterialTheme.typography.labelMedium,
                color = color,
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        },
        icon = {
            Box(
                contentAlignment = Alignment.TopEnd
            ){
                bubbleValue.let {
                    if (it !== null && it.value != 0){
                        Box(modifier = Modifier
                            .size(dimensionResource(R.dimen.bottom_bar_bubble_size))
                            .background(Color.Red, shape = RoundedCornerShape(dimensionResource(R.dimen.bottom_bar_bubble_corner)))
                            .zIndex(4f),
                            contentAlignment = Alignment.Center){
                            Text(
                                text = it.value.toString(),
                                style = MaterialTheme.typography.labelSmall,
                                color = MaterialTheme.colorScheme.onSurface
                            )
                        }
                    }
                }

                Icon(
                    painter = painterResource(screen.icon),
                    contentDescription = "Navigation Icon",
                    tint = color,
                    modifier = Modifier.size(dimensionResource(R.dimen.ic_bottom_bar_size))
                )
            }
        },
        selected = currentDestination?.hierarchy?.any {
            it.route == screenRoute
        } == true,
        selectedContentColor = MaterialTheme.colorScheme.secondary,
        unselectedContentColor = MaterialTheme.colorScheme.onSurfaceVariant,
        onClick = {
            if(screenRoute != currentDestination?.route)
            navController.navigate(screenRoute) {
                popUpTo(navController.graph.findStartDestination().id)
                launchSingleTop = true
            }
        }
    )
}