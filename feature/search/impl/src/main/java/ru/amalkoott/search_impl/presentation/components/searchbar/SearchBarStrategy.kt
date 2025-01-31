package ru.amalkoott.search_impl.presentation.components.searchbar

import androidx.compose.runtime.Composable

interface SearchBarStrategy {
    @Composable
    fun display(onActionClick: () -> Unit, onFilterClick: () -> Unit)
}