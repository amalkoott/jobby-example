package ru.amalkoott.search_impl.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.layout
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.unit.dp
import ru.amalkoott.common.R
import ru.amalkoott.common.components.LoadingFiller
import ru.amalkoott.model.Offer
import ru.amalkoott.search_impl.presentation.components.offer.OfferCard
import ru.amalkoott.search_impl.presentation.components.offer.OfferStrategy

@Composable()
fun OffersBar(
    isOfferLoading: Boolean,
    offers: List<Offer>,
    getDisplayStrategy: (Offer) -> OfferStrategy
){
    val x = dimensionResource(R.dimen.offers_bar_horizontal_padding)
    val placeable_x = dimensionResource(R.dimen.offers_bar_horizontal_placeable_padding)
    Row(
        modifier = Modifier
            .padding(
                bottom = dimensionResource(R.dimen.offers_bar_bottom_padding),
                top = dimensionResource(R.dimen.offers_bar_top_padding)
            )
            .fillMaxWidth()
            .layout{measurable, constraints ->
                val placeable = measurable.measure(constraints.copy(
                    maxWidth = constraints.maxWidth + x.roundToPx(),
                ))
                layout(placeable.width, placeable.height){
                    placeable.place(placeable_x.roundToPx(),0)
                }
            }
    ) {

        LazyRow(horizontalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.offers_bar_horizontal_placeable_padding)),
        ) {
            if (isOfferLoading) {
                item{
                    Spacer(Modifier.width(dimensionResource(R.dimen.offers_bar_horizontal_placeable_padding)))
                }
                items(3) {
                    LoadingFiller(Modifier.size(
                        height = dimensionResource(R.dimen.offers_card_height),
                        width = dimensionResource(R.dimen.offers_card_width))
                    )
                }
                item{
                    Spacer(Modifier.width(dimensionResource(R.dimen.offers_bar_horizontal_padding)))
                }
            }else{
                item{
                    Spacer(Modifier.width(dimensionResource(R.dimen.offers_bar_horizontal_placeable_padding)))
                }
                items(items = offers){ offer ->
                    val strategy = getDisplayStrategy(offer)
                    OfferCard(offer, strategy)
                }
                item{
                    Spacer(Modifier.width(dimensionResource(R.dimen.offers_bar_horizontal_padding)))
                }
            }
        }
    }
}