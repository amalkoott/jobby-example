package ru.amalkoott.favoritesimpl.presentation.components

import androidx.compose.runtime.Composable
import ru.amalkoott.favoritesimpl.presentation.strategy.VacancyCardDisplayStrategy
import ru.amalkoott.model.Vacancy

@Composable
fun VacancyCard(
    vacancy: Vacancy,
    strategy: VacancyCardDisplayStrategy,
    onClick: (Vacancy) -> Unit,
    onFavoriteClick: (Vacancy) -> Unit) {
    strategy.display(vacancy,onClick,onFavoriteClick)
}