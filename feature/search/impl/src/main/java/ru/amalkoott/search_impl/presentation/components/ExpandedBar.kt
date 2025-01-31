package ru.amalkoott.search_impl.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import ru.amalkoott.common.R
import ru.amalkoott.common.getVacancyText

@Composable
fun ExpandedBar(
    vacanciesCount: Int
){
    Row(modifier = Modifier
        .padding(
            top = dimensionResource(R.dimen.expanded_bar_top_padding),
            bottom = dimensionResource(R.dimen.expanded_bar_bottom_padding),
            start = dimensionResource(R.dimen.expanded_bar_horizontal_padding),
            end = dimensionResource(R.dimen.expanded_bar_horizontal_padding))
        .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically) {
        Text(
            text = getVacancyText(vacanciesCount),
            color = MaterialTheme.colorScheme.onSurface,
            style = MaterialTheme.typography.bodySmall
        )
        Row(verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.by_match_title_horizontal_padding)),) {
            Text(
                text = stringResource(R.string.expandedBarTitle),
                color = MaterialTheme.colorScheme.secondary,
                style = MaterialTheme.typography.bodySmall
            )
            Icon(
                painter = painterResource(R.drawable.ic_launcher_sorter_foreground),
                contentDescription = "",
                tint = MaterialTheme.colorScheme.secondary,
                modifier = Modifier.size( dimensionResource(R.dimen.by_match_ic_size)))
        }
    }
}