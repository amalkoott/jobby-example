package ru.amalkoott.common.components.strategy

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import ru.amalkoott.common.R
import ru.amalkoott.common.formatDate
import ru.amalkoott.common.getPeopleText
import ru.amalkoott.model.Vacancy

class NowLookingVacancyCard : VacancyCardDisplayStrategy {
    @Composable
    override fun display(
        vacancy: Vacancy,
        onClick: (Vacancy) -> Unit,
        onFavoriteClick: (Vacancy) -> Unit) {
        Card(modifier = Modifier
            .fillMaxWidth()
            .padding(
                bottom = dimensionResource(R.dimen.card_bottom_padding),
                start = dimensionResource(R.dimen.card_horisontal_padding),
                end = dimensionResource(R.dimen.card_horisontal_padding))
            .clickable { onClick(vacancy) },
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.surface,
            ),)
        {
            Column(modifier = Modifier
                .padding(dimensionResource(R.dimen.card_content_padding))
                .fillMaxWidth()) {
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = dimensionResource(R.dimen.card_under_responce_btn_padding)),
                ) {
                    Column(modifier = Modifier
                        .weight(1f)) {
                        Column(modifier = Modifier
                            .fillMaxWidth(),
                            verticalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.card_child_padding))){
                            Text(
                                text = "${stringResource(R.string.nowLookingTitle)} ${getPeopleText(vacancy.lookingNumber!!)}",
                                style = MaterialTheme.typography.bodySmall,
                                color = MaterialTheme.colorScheme.tertiary
                            )
                            Text(
                                text = vacancy.title.toString(),
                                style = MaterialTheme.typography.headlineMedium,
                                color = MaterialTheme.colorScheme.onSurface
                            )
                            if(vacancy.salary != null){
                                val text = vacancy.salary!!["short"]?.replace(" до ", "-").toString()
                                if(text !== "null"){
                                    Text(
                                        text = text,
                                        style = MaterialTheme.typography.bodyLarge,
                                        color = MaterialTheme.colorScheme.onSurface
                                    )
                                }
                            }
                            Text(
                                text = vacancy.town.toString(),
                                style = MaterialTheme.typography.bodySmall,
                                color = MaterialTheme.colorScheme.onSurface
                            )
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.card_row_padding))
                            ){
                                Text(
                                    text = vacancy.company.toString(),
                                    style = MaterialTheme.typography.bodySmall,
                                    color = MaterialTheme.colorScheme.onSurface
                                )
                                Icon(
                                    painter = painterResource(R.mipmap.ic_launcher_confirm_foreground),
                                    contentDescription = "confirmed company",
                                    modifier = Modifier
                                        .size(dimensionResource(R.dimen.ic_confirm_size)),
                                    tint = MaterialTheme.colorScheme.onSurfaceVariant
                                )
                            }

                            Row(
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Icon(
                                    painterResource(R.mipmap.ic_launcher_expierense_foreground), contentDescription = "",
                                    modifier = Modifier
                                        .padding(end = dimensionResource(R.dimen.ic_expiriense_end_padding))
                                        .size(dimensionResource(R.dimen.ic_expiriense_size)))
                                Text(
                                    text = vacancy.experiencePreview.toString(),
                                    style = MaterialTheme.typography.bodySmall,
                                    color = MaterialTheme.colorScheme.onSurface
                                )
                            }
                            Text(
                                text = "${stringResource(R.string.published)} " + formatDate(vacancy.publishedDate.toString()),
                                style = MaterialTheme.typography.bodySmall,
                                color = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                        }
                    }

                    Column(modifier = Modifier
                        .padding(start = dimensionResource(R.dimen.ic_favorite_start_padding))) {
                        Box(modifier = Modifier
                            .size(dimensionResource(R.dimen.ic_favorite_size))
                            .clip(CircleShape)
                            .clickable { onFavoriteClick(vacancy) }
                        ){
                            Icon(
                                painter = painterResource(if (vacancy.isFavorite) R.mipmap.ic_launcher_favorite_filled_foreground else R.drawable.ic_launcher_favorite_foreground),
                                contentDescription = "",
                                modifier = Modifier.fillMaxSize(),
                                tint = if(vacancy.isFavorite) MaterialTheme.colorScheme.secondary else MaterialTheme.colorScheme.onSecondary)
                        }
                    }
                }
                Button(
                    onClick = { },
                    modifier = Modifier
                        .fillMaxWidth(),
                    shape = RoundedCornerShape(dimensionResource(R.dimen.card_response_btn_corner)),
                    colors = ButtonColors(
                        containerColor = MaterialTheme.colorScheme.tertiary,
                        contentColor = MaterialTheme.colorScheme.onSurface,
                        disabledContentColor = MaterialTheme.colorScheme.tertiaryContainer,
                        disabledContainerColor = MaterialTheme.colorScheme.onSecondary,
                    )
                ) {
                    Text(
                        text = stringResource(R.string.toResponseBtn),
                        style = MaterialTheme.typography.displaySmall,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                }
            }
        }
    }
}