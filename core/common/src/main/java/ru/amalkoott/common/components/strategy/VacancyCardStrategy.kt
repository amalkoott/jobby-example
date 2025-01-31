package ru.amalkoott.common.components.strategy

import androidx.compose.runtime.Composable
import ru.amalkoott.model.Vacancy

interface VacancyCardDisplayStrategy {
    @Composable
    fun display(
        vacancy: Vacancy,
        onClick: (Vacancy) -> Unit,
        onFavoriteClick: (Vacancy) -> Unit)
}