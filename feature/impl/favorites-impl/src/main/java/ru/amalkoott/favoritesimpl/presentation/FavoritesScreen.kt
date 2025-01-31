package ru.amalkoott.favoritesimpl.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.material3.Text
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import ru.amalkoott.common.getVacancyText
import ru.amalkoott.favoritesimpl.presentation.components.VacancyCard
import ru.amalkoott.model.Vacancy
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun FavoritesScreen(
    name: String, onClick: () -> Unit, viewModel: FavoritesViewModel = hiltViewModel()
/*
    favorites: List<Vacancy>,
    strategy: VacancyCardDisplayStrategy,
    onVacancyClick: () -> Unit,
    onFavoriteClick:() -> Unit
    */
){
    val favorites by viewModel.favorites.collectAsState(initial = emptyList())
    val onFavoriteClick:(Vacancy) -> Unit = { vacancy -> viewModel.onRemoveFavorite(vacancy)}
    val onVacancyClick:(Vacancy) -> Unit = { vacancy -> viewModel.openVacancy(vacancy); onClick() }

    LazyColumn(modifier =  Modifier,
        verticalArrangement = Arrangement.spacedBy(8.dp)) {
        item {
            Text(
                text = name,
                color = MaterialTheme.colorScheme.onSurface,
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier
                    .padding(top = 56.dp, bottom = 24.dp, start = 16.dp, end = 16.dp)
            )

            Text(
                text = getVacancyText(favorites.size),
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                style = MaterialTheme.typography.bodySmall,
                modifier = Modifier
                    .padding(bottom = 8.dp, start = 16.dp, end = 16.dp)
            )
        }
        items(favorites){ vacancy ->
            val strategy = viewModel.getDisplayStrategy(vacancy)
            VacancyCard(vacancy, strategy,onVacancyClick, onFavoriteClick)
        }
        item{
            Spacer(modifier = Modifier.padding(26.dp))
        }
    }

}