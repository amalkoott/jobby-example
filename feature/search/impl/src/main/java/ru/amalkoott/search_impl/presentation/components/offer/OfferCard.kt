package ru.amalkoott.search_impl.presentation.components.offer

import androidx.compose.runtime.Composable
import ru.amalkoott.model.Offer

@Composable
fun OfferCard(
    offer: Offer,
    strategy: OfferStrategy
) {
    strategy.display(offer)
}