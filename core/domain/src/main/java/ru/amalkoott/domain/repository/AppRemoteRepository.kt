package ru.amalkoott.domain.repository

import kotlinx.coroutines.flow.Flow
import ru.amalkoott.model.Offer
import ru.amalkoott.model.Vacancy

interface AppRemoteRepository {
    suspend fun get(): Pair<Flow<List<Offer>>,Flow<List<Vacancy>>>?
}