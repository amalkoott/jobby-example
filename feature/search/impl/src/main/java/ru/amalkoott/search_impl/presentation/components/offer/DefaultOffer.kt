package ru.amalkoott.search_impl.presentation.components.offer

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.unit.dp
import ru.amalkoott.common.R
import ru.amalkoott.model.Offer

class DefaultOffer : OfferStrategy {
    @Composable
    override fun display(offer: Offer) {
        Card(
            modifier = Modifier
                .size(
                    width = dimensionResource(R.dimen.offers_card_width),
                    height = dimensionResource(R.dimen.offers_card_height)),
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.surface
            )
        ) {
            Column(
                modifier = Modifier
                    .padding(
                        top = dimensionResource(R.dimen.offers_card_content_padding_top),
                        start = dimensionResource(R.dimen.offers_card_content_padding_start),
                        end = dimensionResource(R.dimen.offers_card_content_padding_end),
                        bottom = dimensionResource(R.dimen.offers_card_content_padding_bottom)
                    )
                    .fillMaxSize(),
                verticalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.offers_card_element_vertical_padding))
            ) {
                OfferIcon(offer.id)
                Column {
                    offer.title?.let {
                        Text(
                            text = it.trimStart(),
                            style = MaterialTheme.typography.titleSmall,
                            color = MaterialTheme.colorScheme.onSurface,
                            maxLines = 3
                        )
                    }
                }
            }
        }
    }
}