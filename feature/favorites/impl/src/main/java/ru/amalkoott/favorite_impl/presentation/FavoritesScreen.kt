package ru.amalkoott.favorite_impl.presentation

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
import androidx.compose.ui.res.dimensionResource
import ru.amalkoott.common.getVacancyText
import ru.amalkoott.model.Vacancy
import androidx.hilt.navigation.compose.hiltViewModel
import ru.amalkoott.common.components.VacancyCard
import ru.amalkoott.common.R

@Composable
fun FavoritesScreen(
    name: String, onClick: (Vacancy) -> Unit,
    viewModel: FavoritesViewModel = hiltViewModel()
){
    val favorites by viewModel.favorites.collectAsState(initial = emptyList())
    val onFavoriteClick:(Vacancy) -> Unit = { vacancy -> viewModel.onRemoveFavorite(vacancy)}
    val onVacancyClick:(Vacancy) -> Unit = { vacancy -> viewModel.openVacancy(vacancy); onClick(vacancy) }

    LazyColumn(modifier =  Modifier,
        verticalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.favorites_element_vertical_padding))) {
        item {
            Text(
                text = name,
                color = MaterialTheme.colorScheme.onSurface,
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier
                    .padding(
                        top = dimensionResource(R.dimen.favorites_title_top_padding),
                        bottom = dimensionResource(R.dimen.favorites_title_bottom_padding),
                        start = dimensionResource(R.dimen.favorites_title_horizontal_padding),
                        end = dimensionResource(R.dimen.favorites_title_horizontal_padding))
            )

            Text(
                text = getVacancyText(favorites.size),
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                style = MaterialTheme.typography.bodySmall,
                modifier = Modifier
                    .padding(
                        bottom = dimensionResource(R.dimen.vacancy_text_bottom_padding),
                        start = dimensionResource(R.dimen.vacancy_text_horizontal_padding),
                        end = dimensionResource(R.dimen.vacancy_text_horizontal_padding))
            )
        }
        items(favorites){ vacancy ->
            val strategy = viewModel.getDisplayStrategy(vacancy)
            VacancyCard(vacancy, strategy,onVacancyClick, onFavoriteClick)
        }
        item{
            Spacer(modifier = Modifier.padding(dimensionResource(R.dimen.favorites_layout_bottom_padding)))
        }
    }

}