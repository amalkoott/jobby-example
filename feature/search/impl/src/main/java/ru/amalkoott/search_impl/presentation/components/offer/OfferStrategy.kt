package ru.amalkoott.search_impl.presentation.components.offer

import androidx.compose.runtime.Composable
import ru.amalkoott.model.Offer

interface OfferStrategy {
    @Composable
    fun display(offer: Offer)
}