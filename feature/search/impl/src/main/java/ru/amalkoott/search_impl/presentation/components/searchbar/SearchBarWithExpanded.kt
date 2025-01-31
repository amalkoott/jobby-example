package ru.amalkoott.search_impl.presentation.components.searchbar

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import ru.amalkoott.common.R

class SearchBarWithExpanded : SearchBarStrategy {
    @Composable
    override fun display(onActionClick: () -> Unit, onFilterClick: () -> Unit) {
        SearchBar(
            value = "",
            onValueChange = {},
            label = "String",
            leadingIcon = R.drawable.ic_launcher_left_arrow_foreground,
            modifier = Modifier
                .padding(
                    top = dimensionResource(R.dimen.search_bar_top_padding))
                .height(dimensionResource(R.dimen.search_bar_expanded_height))
                .fillMaxWidth(),
            textColor = MaterialTheme.colorScheme.onSurface,
            contentColor = MaterialTheme.colorScheme.onSurface,
            toolColor = MaterialTheme.colorScheme.onSurface,
            backgroundColor = MaterialTheme.colorScheme.surfaceVariant,
            placeholder = stringResource(R.string.expandedSearchBarPlaceholder),
            placeholderColor = MaterialTheme.colorScheme.onSurfaceVariant,
            padding = dimensionResource(R.dimen.search_bar_padding),
            navigateAction = { onActionClick() },
            extraAction = { onFilterClick() }
        )

    }
}